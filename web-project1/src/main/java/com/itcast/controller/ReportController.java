package com.itcast.controller;

import com.itcast.pojo.ClazzCountOption;
import com.itcast.pojo.JobOption;
import com.itcast.pojo.Result;
import com.itcast.service.EmpService;
import com.itcast.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private EmpService EmpService;
    @Autowired
    private StudentService StudentService;
    /**
     * 员工数据统计
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("员工数据统计");
        JobOption jobOption =EmpService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 员工性别数据统计
     * @return
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){

        log.info("员工性别数据统计");
        List<Map<String,Object>> genderDataList = EmpService.getEmpGenderData();
        return Result.success(genderDataList);
    }

    /**
     * 学员学历统计
     * @return
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){

        log.info("学员学历统计");
        List<Map<String,Object>> degreeDataList = StudentService.getStudentDegreeData();
        return Result.success(degreeDataList);
    }

    /**
     * 班级人数统计
     * @return
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){

        log.info("班级人数统计");
        ClazzCountOption clazzCountOption = StudentService.getStudentCountData();
        return Result.success(clazzCountOption);
    }
}
