package com.itcast.controller;

import com.itcast.pojo.Emp;
import com.itcast.pojo.LoginInfo;
import com.itcast.pojo.Result;
import com.itcast.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp  emp) {
        log.info("员工登录，员工信息：{}",emp);

        LoginInfo loginInfo  = empService.getLoginInfo(emp);

        if (loginInfo == null){
            return Result.error("用户名或密码错误");
        }
        return Result.success(loginInfo);
    }
}
