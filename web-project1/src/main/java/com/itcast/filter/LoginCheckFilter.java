package com.itcast.filter;

import com.itcast.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Description LoginCheckFilter
 * @Author songyu
 * @Date 2025-01-11  17:01
 */
@Slf4j
@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 1．获取请求url。
        // ServletRequest 是父接口，功能少
        // HttpServletRequest 是子接口，功能多,这个里面有获取当前的请求路径
        // 需要将父接口转换为子接口
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取当前请求路径
        //     request.getRequestURI(); 获取的路径是 /emps
        //     request.getRequestURL(); 获取的路径是 http:/localhost:8080/emps
        String path = request.getRequestURI();

        // 2。判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if(path.contains("/login")){
            filterChain.doFilter(request, response);
            return;//结束，不可以往下走，下面是校验token
        }

        // 3．获取请求头中的令牌（token）。
        String token = request.getHeader("token");//请求头中的key不区分大小写
        // 4。判断令牌是否存在，如果不存在，响应401。
        if(token==null || token.equals("")){
            response.setStatus(401);
            return;
        }
        // 5．解析token，如果解析失败，响应401
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Object id = claims.get("id");
            log.info("员工id：{}登录校验成功了", id);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(401);
            return;
        }
        // 6．放行。
        filterChain.doFilter(request, response);
    }
}
