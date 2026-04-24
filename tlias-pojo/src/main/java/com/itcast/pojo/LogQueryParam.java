package com.itcast.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogQueryParam {
    private Integer operateEmpId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime operateTime;
    private String className; //操作类名
    private String methodName; //操作方法名
    private String methodParams; //操作方法参数
    private String returnValue; //操作方法返回值
    private Long costTime; //操作耗时
    private Integer page = 1;
    private Integer pageSize = 10;

    private String operateEmpName; //操作人姓名
}