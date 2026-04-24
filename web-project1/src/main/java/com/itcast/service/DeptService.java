package com.itcast.service;

import com.itcast.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> list();

    void insert(Dept dept);

    void delete(Integer id);

    Dept getById(Integer id);

    void update(Dept dept);
}
