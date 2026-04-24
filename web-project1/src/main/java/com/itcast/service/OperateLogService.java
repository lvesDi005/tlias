package com.itcast.service;

import com.itcast.pojo.LogQueryParam;
import com.itcast.pojo.OperateLog;
import com.itcast.pojo.PageResult;

/**
 * @Description OperateLogService
 * @Author kight-tom
 * @Date 2026-04-15  18:10
 */
public interface OperateLogService{


    PageResult<OperateLog> page(LogQueryParam logQueryParam);

}
