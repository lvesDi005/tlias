package com.itcast.controller;

import com.itcast.pojo.*;
import com.itcast.service.EmpService;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 员工管理Controller
 */
@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {

    @Autowired
    private EmpService EmpService;

    /**
     * 分页查询
     * @RequestParam 设置请求参数的默认值
     * @return
     */
    /*
    查询全部员工
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有员工");
        List<Emp> empList = EmpService.list();
        return Result.success(empList);
    }

/*    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam (defaultValue = "10") Integer pageSize,
                       String name, Integer  gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询,当前页码{},每页记录数{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageResult<Emp> pageResult = EmpService.page(page,pageSize);
        return Result.success(pageResult);
    }*/
    //@LogOperator
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}",empQueryParam);
        //PageResult<Emp> pageResult = EmpService.page(page,pageSize);
        PageResult pageResult = EmpService.page(empQueryParam);
        return Result.success(pageResult);

    }

    /**
     * 根据员工ID查询员工信息和工作经历
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("查询员工ID：{}",id);
        Emp emp = EmpService.getById(id);
        return Result.success(emp);
    }

    /**
     * 新增员工
     */
    //@LogOperator
    @PostMapping
    public Result insert(@Validated(Emp.InsertGroup.class) @RequestBody Emp emp){
        log.info("新增员工，员工信息：{}",emp);
        EmpService.insert(emp);
        return Result.success();
    }

    @PutMapping
    public Result update(@Valid @RequestBody Emp emp){
        log.info("修改员工，员工信息：{}",emp);
        EmpService.update(emp);
        return Result.success();
    }

    /**
     * 删除员工
     */
    @DeleteMapping
    public Result delete(Integer[] ids){
        log.info("删除员工，员工ID：{}",ids);
        EmpService.delete(ids);
        return Result.success();
    }

    /**
     * 查询班主任列表
     *//*
    @GetMapping("/teachers")
    public Result getTeacherList(){
        log.info("查询班主任列表");
        List<Map<String, Object>> teacherList = EmpService.getTeacherList();
        // 转换为前端下拉框需要的格式 {value, label}
        List<Map<String, Object>> resultList = teacherList.stream().map(map -> {
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("value", map.get("id"));
            newMap.put("label", map.get("name"));
            return newMap;
        }).toList();
        return Result.success(resultList);
    }
*/
}
