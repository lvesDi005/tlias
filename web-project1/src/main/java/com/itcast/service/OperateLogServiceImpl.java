package com.itcast.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itcast.mapper.OperateLogMapper;
import com.itcast.pojo.LogQueryParam;
import com.itcast.pojo.OperateLog;
import com.itcast.pojo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description OperateLogServiceImpl
 * @Author kight-tom
 * @Date 2026-04-15  18:12
 */
@Service
@Slf4j
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(LogQueryParam logQueryParam) {

        PageHelper.startPage(logQueryParam.getPage(), logQueryParam.getPageSize());
        List<OperateLog> OperateLogList = operateLogMapper.list(logQueryParam);
        Page<OperateLog> pageResult = (Page<OperateLog>) OperateLogList;
        return new PageResult<>(pageResult.getTotal(),pageResult.getResult());
    }
}
