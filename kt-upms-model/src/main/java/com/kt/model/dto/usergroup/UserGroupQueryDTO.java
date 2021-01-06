package com.kt.model.dto.usergroup;


import com.kt.component.dto.PagingDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserGroupQueryDTO extends PagingDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户名称
     */
    private String name;

}
