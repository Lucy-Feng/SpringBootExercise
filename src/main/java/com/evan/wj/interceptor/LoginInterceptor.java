package com.evan.wj.interceptor;

import com.evan.wj.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**拦截器的逻辑：
 * 1.用户访问 URL，检测是否为登录页面，如果是登录页面则不拦截；
 * 2.如果不是登录页面，检测用户是否已登录，如果未登录则跳转到登录页面，如果已登录则不拦截。
 * **/
//后端拦截器只有在将前后端项目整合在一起时才能生效，前后端分离开发的时候不推荐这样做
// 登录拦截器,继承拦截器的接口
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    // 在访问需要拦截的页面时执行该方法
    // 判断 session 中是否存在 user 属性，如果存在就放行，如果不存在就跳转到 login 页面
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String contextPath=session.getServletContext().getContextPath();
        // 路径列表（requireAuthPages），存放需要拦截的路径
        String[] requireAuthPages = new String[]{
                "index",
        };

        String uri = httpServletRequest.getRequestURI();

        uri = StringUtils.remove(uri, contextPath+"/");
        String page = uri;

        if(beginWith(page, requireAuthPages)){
            User user = (User) session.getAttribute("user");
            if(user==null) {
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean beginWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
