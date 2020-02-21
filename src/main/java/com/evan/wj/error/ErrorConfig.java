package com.evan.wj.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
//在后端添加处理内容，把 通过这个 URL 渲染出的 index.html 返回到浏览器
//把默认的错误页面设置为 /index.html
public class ErrorConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND,"/index.html");
        registry.addErrorPages(errorPage);

    }
}
