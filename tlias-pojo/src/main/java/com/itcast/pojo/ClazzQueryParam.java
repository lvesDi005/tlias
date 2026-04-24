package com.itcast.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzQueryParam {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String end;
    private Integer page = 1;
    private Integer pageSize = 10;
}