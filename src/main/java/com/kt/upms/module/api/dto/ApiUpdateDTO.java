package com.kt.upms.module.api.dto;

import com.kt.component.validator.ValidateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ApiUpdateDTO implements Serializable {

    @NotNull(message = "id不能为空", groups = {ValidateGroup.Update.class})
    private Long id;

    @NotNull(message = "applicationId不能为空", groups = {ValidateGroup.Add.class})
    private Long applicationId;

    @NotBlank(message = "name不能为空", groups = {ValidateGroup.Add.class})
    private String name;

    @NotBlank(message = "url不能为空", groups = {ValidateGroup.Add.class})
    private String url;

    @NotBlank(message = "url不能为空", groups = {ValidateGroup.Add.class})
    private Integer method;

    @NotNull(message = "authType不能为空", groups = {ValidateGroup.Add.class})
    private Integer authType;

    @NotNull(message = "status不能为空", groups = {ValidateGroup.Add.class})
    private Integer status;

}