package com.itcast.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itcast.mapper.StudentMapper;
import com.itcast.pojo.ClazzCountOption;
import com.itcast.pojo.PageResult;
import com.itcast.pojo.Student;
import com.itcast.pojo.StudentQueryParam;
import com.itcast.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StudentImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> studentList = studentMapper.list(studentQueryParam);

        Page<Student> p = (Page<Student>) studentList;
        return new PageResult(p.getTotal(), p.getResult());

    }

    @Override
    public void delete(Integer[] ids) {

        studentMapper.delete(ids);
    }

    @Override
    public void insert(Student student) {

        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);

    }

    @Override
    public Student getById(Integer id) {

        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {

        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void violation(Integer id, Integer score) {

        //1.根据id查询
        Student student = studentMapper.getById(id);
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount(student.getViolationCount() + 1);
        student.setViolationScore(student.getViolationScore() + score);
        studentMapper.update(student);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {

        return studentMapper.getStudentDegreeData();
    }

    @Override
    public ClazzCountOption getStudentCountData() {

        List<Map> clazzCountList = studentMapper.getStudentCountData();

        //获取班级集合
        List clazzList = clazzCountList.stream().map(map -> {
            return map.get("name");
        }).toList();

        //获取人数集合
        List dataList = clazzCountList.stream().map(map -> {
            return map.get("num");
        }).toList();

        return new ClazzCountOption(clazzList, dataList);

    }
}
