package com.itcast.mapper;

import com.itcast.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询所有部门
     * @return
     */
    //1.手动封装
/*    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })*/
    //2.起别名
    //@Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc")

    //按照update_time倒序
    @Select("select * from dept order by update_time desc")
    List<Dept> list();

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 删除部门
     * @param id
     */
    //@Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    @Select("select count(*) from emp where dept_id = #{deptId}")
    long countEmpByDeptId(Integer deptId);

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @Select("select * from dept where id=#{id}")
    Dept getById(Integer id);

    @Update("update dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);

}
