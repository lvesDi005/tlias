package com.itcast.controller;

import com.itcast.pojo.PageResult;
import com.itcast.pojo.Result;
import com.itcast.pojo.Student;
import com.itcast.pojo.StudentQueryParam;
import com.itcast.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("查询学生列表: {}", studentQueryParam);
        PageResult pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 根据id查询学员
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询学员ID: {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @DeleteMapping("{ids}")
    public Result delete(@PathVariable Integer[] ids) {
        log.info("删除学生: {}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Student student) {
        log.info("添加学生: {}", student);
        studentService.insert(student);
        return Result.success();
    }



    /**
     * 修改学员信息
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改员工，员工信息：{}",student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("违纪处理，员工ID：{}，扣分：{}",id,score);
        studentService.violation(id,score);
        return Result.success();
    }
}
