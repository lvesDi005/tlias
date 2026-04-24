package com.itcast.mapper;

import com.itcast.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /**
     * 批量插入员工工作经历
     * @param empExprList
     */
    void InsertBatch(List<EmpExpr> empExprList);


    void delete(Integer[] ids);

}
