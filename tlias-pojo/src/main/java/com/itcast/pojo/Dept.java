package com.itcast.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    private String name;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
