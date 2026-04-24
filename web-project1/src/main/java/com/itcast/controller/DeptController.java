package com.itcast.controller;

import com.itcast.mapper.DeptMapper;
import com.itcast.pojo.Dept;
import com.itcast.pojo.Result;
import com.itcast.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@RestController
@RequestMapping("/depts")
@Slf4j
public class DeptController {

    //定义日志记录器
    //private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询部门列表
     * @return
     */
    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list () {
        log.info("查询部门列表");
        List<Dept> deptlist = deptService.list();
        return Result.success(deptlist);
    }

    /**
     * 新增部门
     * @param dept
     * @return
     */
    @PostMapping
    //@RequestBody 映射json数据
    public Result insert(@RequestBody Dept dept) {
        log.info("新增部门：{}", dept);
        deptService.insert(dept);
        return Result.success();
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @DeleteMapping
    //@RequestParam(name = "id")
    public Result delete(Integer id) {
        log.info("删除部门：{}", id);
        long count = deptMapper.countEmpByDeptId(id);
        if (count > 0) {
            //throw new RuntimeException("该部门下有员工，不能删除");
            return Result.error("该部门下有员工，不能删除");
        }
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    //@PathVariable("id") Integer id
    public Result getInfo(@PathVariable Integer id){
        log.info("查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return  Result.success(dept);
    }

    /**
     * 修改部门
     * @param dept
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
