package com.itcast.service;

import com.itcast.pojo.ClazzCountOption;
import com.itcast.pojo.PageResult;
import com.itcast.pojo.Student;
import com.itcast.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

public interface StudentService {

    PageResult page(StudentQueryParam studentQueryParam);

    void delete(Integer[] ids);

    void insert(Student student);

    Student getById(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);

    List<Map<String, Object>> getStudentDegreeData();


    ClazzCountOption getStudentCountData();
}
