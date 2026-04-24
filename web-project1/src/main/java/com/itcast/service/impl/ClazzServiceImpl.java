package com.itcast.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itcast.mapper.ClazzMapper;
import com.itcast.pojo.Clazz;
import com.itcast.pojo.ClazzQueryParam;
import com.itcast.pojo.PageResult;
import com.itcast.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        // 拓展业务-计算班级状态
        clazzList.forEach(clazz -> {
            LocalDate now = LocalDate.now();
            if (clazz.getBeginDate().isAfter(now)) {
                clazz.setStatus("未开班");
            } else if (clazz.getEndDate().isBefore(now)) {
                clazz.setStatus("已结课");
            } else {
                clazz.setStatus("在读");
            }
        });

        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }

    @Override
    public void insert(Clazz clazz) {

        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazz.setStatus("未开班");
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {

        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {

        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> list() {

        return clazzMapper.list(null);
    }
}
