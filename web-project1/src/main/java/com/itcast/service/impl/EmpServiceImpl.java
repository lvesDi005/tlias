package com.itcast.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itcast.mapper.EmpExprMapper;
import com.itcast.mapper.EmpMapper;
import com.itcast.pojo.*;
import com.itcast.service.EmpService;
import com.itcast.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper EmpMapper;
    @Autowired
    private EmpExprMapper EmpExprMapper;

    @Override
    public Emp getById(Integer id) {
        return EmpMapper.getById(id);
    }

    @Override
    public PageResult page(EmpQueryParam empQueryParam) {

/*        //1. 查询总记录数
        Long total = EmpMapper.count();
        //2. 查询分页结果列表
        Integer start = (page-1)*pageSize;
        //3. 封装PageResult
        List<Emp> empList = EmpMapper.list(start,pageSize);
        return new PageResult<>(total,empList);*/

        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.查询分页结果列表
        List<Emp> empList = EmpMapper.list(empQueryParam);
        Page<Emp> pageResult = (Page<Emp>) empList;
        //3.封装PageResult
        return new PageResult<>(pageResult.getTotal(),pageResult.getResult());
    }

    @Transactional
    @Override
    public void insert(Emp emp) {

        log.info("接收到的员工信息：{}", emp);
        log.info("工作经历列表：{}", emp.getExprList());

        //1.保存员工的基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        EmpMapper.insert(emp);
        //2.保存员工工作信息
        List<EmpExpr> empExprList = emp.getExprList();
        if (empExprList != null && !empExprList.isEmpty()){
            empExprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            EmpExprMapper.InsertBatch(empExprList);
        }

    }

    @Transactional
    @Override
    public void delete(Integer[] ids) {

        log.info("删除员工，员工ID：{}",ids);
        EmpExprMapper.delete(ids);
        EmpMapper.delete(ids);
    }

    @Transactional
    @Override
    public void update(Emp emp) {

        log.info("修改员工，员工信息：{}",emp);
        //1.更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        EmpMapper.update(emp);

        //2.删除旧的工作经历
        EmpExprMapper.delete(new Integer[]{emp.getId()});

        //3.保存新的工作经历
        if (emp.getExprList() != null && !emp.getExprList().isEmpty()){
            emp.getExprList().forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            EmpExprMapper.InsertBatch(emp.getExprList());
        }

    }

    @Override
    public JobOption getEmpJobData() {
        List<Map> jobDataList =EmpMapper.getEmpJobData();

        //获取职位集合

        List joblist = jobDataList.stream().map(map -> {
            return map.get("pos");
        }).toList();
        List datalist = jobDataList.stream().map(map -> {
            return map.get("num");
        }).toList();

        return new JobOption(joblist,datalist);
    }

    @Override
    public List<Map<String,Object>> getEmpGenderData() {

        return EmpMapper.getEmpGenderData();
    }

    @Override
    public LoginInfo getLoginInfo(Emp emp) {

        //1.查询员工信息
        Emp e =EmpMapper.findByUsernameAndPassword(emp);

        if (e != null){
            //2.返回登录信息
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setId(e.getId());
            loginInfo.setUsername(e.getUsername());
            loginInfo.setName(e.getName());
            //1.定义载荷
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            //2.生成令牌
            String token = JwtUtils.generateJwt(claims);
            //3.封装令牌
            loginInfo.setToken(token);

            return loginInfo;
        }
        return null;
    }


}
