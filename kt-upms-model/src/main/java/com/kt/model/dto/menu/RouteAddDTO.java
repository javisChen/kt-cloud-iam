package com.kt.model.dto.menu;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RouteAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "name 不能为空")
    private String name;

    @NotNull(message = "pid 不能为空")
    private Long pid;

    @NotBlank(message = "code 不能为空")
    private String code;

    private String component;

    private Boolean hideChildren;

    private String path;

    private String icon;



}