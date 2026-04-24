package com.itcast.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description Demo1Interceptor
 * @Author songyu
 * @Date 2025-01-11  18:28
 */
@Component // 非三类组件
public class Demo1Interceptor implements HandlerInterceptor {

    //请求前拦截：返回true代表放行，返回false代表不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("==执行了Demo1Interceptor的preHandle方法==");
        return true;
    }

    //请求执行控制器方法资源后拦截： 前提控制器方法不能发生异常
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("==执行了Demo1Interceptor的postHandle方法==");
    }

    //请求执行控制器方法资源最后拦截： 无论是否发生异常都会执行
    // @Override
    // public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    //     System.out.println("==执行了Demo1Interceptor的afterCompletion方法==");
    // }





}
