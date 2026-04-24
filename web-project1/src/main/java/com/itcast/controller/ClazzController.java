package com.itcast.controller;

import com.itcast.mapper.ClazzMapper;
import com.itcast.pojo.Clazz;
import com.itcast.pojo.ClazzQueryParam;
import com.itcast.pojo.PageResult;
import com.itcast.pojo.Result;
import com.itcast.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 班级列表分页查询
     * @param clazzQueryParam
     * @return
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("班级列表分页查询: {}", clazzQueryParam);
        PageResult pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 删除班级
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级: {}", id);
        long count = clazzMapper.countEmpByDeptId(id);
        if (count > 0) {
            //throw new RuntimeException("该班级下有学生，不能删除");
            return Result.error("该班级下有学生，不能删除");
        }
        clazzService.delete(id);
        return Result.success();
    }

    /**
     * 新增班级
     * @param clazz
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody Clazz clazz) {
        log.info("新增班级: {}", clazz);
        clazzService.insert(clazz);
        return Result.success();
    }

    /**
     * 根据ID查询班级
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询班级ID: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }

}
