package com.itcast.interceptors;

import com.itcast.utils.JwtUtils;
import com.itcast.utils.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description LoginCheckInterceptor
 * @Author songyu
 * @Date 2025-01-11  20:38
 */
@Slf4j
@Component//创建对象并加入spring容器
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 3．获取请求头中的令牌（token）。
        String token = request.getHeader("token");//请求头中的key不区分大小写
        // 4。判断令牌是否存在，如果不存在，响应401。
        if(token==null || token.equals("")){
            response.setStatus(401);
            return false;
        }
        // 5．解析token，如果解析失败，响应401
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Object id = claims.get("id");
            log.info("员工id：{}登录校验成功了", id);

            //6.将员工id存入ThreadLocal
            ThreadLocalUtil.setLoginId((Integer) id);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(401);
            return false;
        }
        // 7．放行。
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        //ThreadLocal缺点:内存泄漏
        //就是线程局部变量里面的存储的数新没有清空就放回线程池。当有新用户请求线程池可能会西次分配这个线程给
        //给用户用，导致上一个用户请求线程数据泄露给当用户。
        //解决方案:线程使用结束前清空当前线程的局部变量
        //如果是拦截器就是afterCompLetion方法是最终结束位置，可以清楚线程数据
        ThreadLocalUtil.removeLoginId();
    }
}
