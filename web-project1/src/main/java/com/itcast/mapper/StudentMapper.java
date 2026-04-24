package com.itcast.mapper;

import com.itcast.pojo.Student;
import com.itcast.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    void delete(Integer[] ids);

    void insert(Student student);

    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    void update(Student student);

    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();


    @MapKey("name")
    List<Map> getStudentCountData();

}
