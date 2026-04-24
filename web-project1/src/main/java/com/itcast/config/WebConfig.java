package com.itcast.config;

import com.itcast.interceptors.Demo1Interceptor;
import com.itcast.interceptors.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description WebConfig
 * @Author songyu
 * @Date 2025-01-11  19:35
 */
@Configuration //spring框架配置类注解
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Demo1Interceptor demo1Interceptor;

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器绑定拦截路径
        // 在spring框架中， “/*” 值代码一层路径的任意字符，“/**”代表任意多层字符
        // “/a/*/b”  题目：A:“/a/123/b” B:“/a/123/c/b” 可以匹配哪个？答：A
        // “/a/**/b”  题目：A:“/a/123/b” B:“/a/123/c/b” 可以匹配哪个？答：A，B
        registry.addInterceptor(demo1Interceptor).addPathPatterns("/**");

        //注册登录校验拦截器，并设置拦截路径，然后还要设置排除路径
        //  excludePathPatterns("/login")设置不拦截的路径
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
