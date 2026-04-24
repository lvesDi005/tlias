package com.itcast.mapper;
import com.itcast.pojo.Clazz;
import com.itcast.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {


    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id=#{id}")
    void delete(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    @Select("select * from clazz where id= #{id}")
    Clazz getById(Integer id);

    void update(Clazz clazz);

    @Select("select count(*) from student where clazz_id = #{clazzId}")
    long countEmpByDeptId(Integer id);
}
