package com.itcast.service;

import com.itcast.pojo.*;

import java.util.List;
import java.util.Map;


public interface EmpService {



    Emp getById(Integer id);

    PageResult page(EmpQueryParam empQueryParam);

    void insert(Emp emp);

    void delete(Integer[] ids);

    void update(Emp emp);

    JobOption getEmpJobData();


    List<Map<String,Object>> getEmpGenderData();

    LoginInfo getLoginInfo(Emp emp);


}
