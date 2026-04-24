package com.itcast.service.impl;

import com.itcast.mapper.DeptMapper;
import com.itcast.pojo.Dept;
import com.itcast.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {

        return deptMapper.list();
    }

    @Override
    public void insert(Dept dept) {

        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void delete(Integer id) {

        deptMapper.delete(id);
    }

    @Override
    public Dept getById(Integer id) {

        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {

        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
