package com.itcast.mapper;

import com.itcast.pojo.Emp;

import com.itcast.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {


    /*    *//**
     * 查询所有员工
     * @return
     *//*
    @Select("select emp.*,dept.name as dept_name from emp left join dept on emp.dept_id = dept.id limit #{start},#{pageSize}")
    public List<Emp> list( Integer start, Integer pageSize);


    *//**
     * 查询符合条件总记录数
     * @return
     *//*
    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
    Long count();*/

/*    @Select("select emp.*,dept.name as dept_name from emp left join dept on emp.dept_id = dept.id")*/
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 根据员工ID查询员工信息和工作经历
     * @param id
     * @return
     */
    Emp getById(Integer id);

    //主键返回
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)" +
            " values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void delete(Integer[] ids);


    void update(Emp emp);

    @MapKey("pos")
    List<Map> getEmpJobData();

    @MapKey("name")
    List<Map<String,Object>> getEmpGenderData();

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp findByUsernameAndPassword(Emp emp);

}
