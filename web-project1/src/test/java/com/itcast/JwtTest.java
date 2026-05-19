package com.itcast;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description JwtTest
 * @Author songyu
 * @Date 2025-01-11  14:50
 */
public class JwtTest {

    @Test
    public void generateJwt(){

        //1.定义载荷
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",100);
        claims.put("username","admin");

        //2.生成令牌
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheima") //设置加密算法
                //.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) //设置12个小时后过期
                .setExpiration(new Date(System.currentTimeMillis())) //立刻过期
                .addClaims(claims)
                .compact();

        //打印令牌
        System.out.println(token);
    }

    @Test
    public void parseJwt(){
        //1.定义一个令牌字符串（假设前端传递过来）
        //String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MzY2MjE3MzgsImlkIjoxMDAsInVzZXJuYW1lIjoiYWRtaW4ifQ.Uu3NhelRSvopBeDzFhtGM862XxzEeXdsA29AH0os_4A";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MzY1NzkyMDksImlkIjoxMDAsInVzZXJuYW1lIjoiYWRtaW4ifQ.F-ojuoAeMs_VKdbmCy2HIruS0jEqkYKtTA9ZJ8ZgcJU";//已经过期
        //2.解析令牌得到载荷
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws(token)
                .getBody();
        //3.打印载荷
        Object id = claims.get("id");
        Object username = claims.get("username");
        System.out.println("id="+id+",username="+username);
    }
}
