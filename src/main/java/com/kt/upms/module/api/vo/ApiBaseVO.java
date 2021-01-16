package com.kt.upms.module.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @title:
 * @desc:
 * @author: Javis
 */
@Data
public class ApiBaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long applicationId;

    private String url;

    private Integer method;

    private Integer authType;

    private Integer status;

}
