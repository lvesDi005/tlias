package com.itcast.service;

import com.itcast.pojo.Clazz;
import com.itcast.pojo.ClazzQueryParam;
import com.itcast.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    PageResult page(ClazzQueryParam clazzQueryParam);

    void delete(Integer id);

    void insert(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    List<Clazz> list();

}
