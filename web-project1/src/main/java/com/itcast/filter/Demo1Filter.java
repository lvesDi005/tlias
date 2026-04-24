package com.itcast.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * @Description Demo1Filter
 * @Author songyu
 * @Date 2025-01-11  16:17
 *
 * 过滤器开发步骤：
 *      1.创建类实现 jakarta.servlet.Filter
 *      2.重写doFilter方法
 *      3.使用@WebFilter注解
 *      4.在启动类上添加注解 @ServletComponentScan
 */
@WebFilter("/*")  // /* 代表拦截任何路径请求
public class Demo1Filter implements Filter {

    /**
     * 拦截请求与响应的方法
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("执行了Demo1Filter拦截请求代码");

        //放行给资源去执行
        filterChain.doFilter(servletRequest,servletResponse);//注意：该放行必须放行，否则资源无法执行

        //执行执行后来到这里继续执行
        System.out.println("执行了Demo1Filter拦截响应代码");
    }
}
