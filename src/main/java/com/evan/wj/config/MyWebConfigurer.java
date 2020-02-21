package com.evan.wj.config;

import com.evan.wj.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@SpringBootConfiguration
//把登录拦截器配置到项目中
public class MyWebConfigurer implements WebMvcConfigurer{
    @Bean
    public LoginInterceptor getLoginIntercepter(){
        return  new LoginInterceptor();
    }

    @Override
    //对所有路径应用拦截器，除了 /index.html
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }
}
