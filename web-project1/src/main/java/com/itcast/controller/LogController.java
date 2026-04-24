package com.itcast.controller;

import com.itcast.pojo.LogQueryParam;
import com.itcast.pojo.OperateLog;
import com.itcast.pojo.PageResult;
import com.itcast.pojo.Result;
import com.itcast.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description LogController
 * @Author kight-tom
 * @Date 2026-04-15  17:26
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @Autowired
    private OperateLogService operateLogService;
    /**
     * 分页查询操作日志
     */
    @GetMapping("/page")
    public Result page(LogQueryParam logQueryParam) {

        PageResult<OperateLog> pageResult = operateLogService.page(logQueryParam);
        return Result.success(pageResult);
    }

}
