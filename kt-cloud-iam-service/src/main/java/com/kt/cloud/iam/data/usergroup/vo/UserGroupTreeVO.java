package com.kt.cloud.iam.data.usergroup.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserGroupTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String key;
    private List<ChildrenBean> children;

    @Data
    public static class ChildrenBean {
        private String title;
        private String key;
        private List<ChildrenBean> children;

    }
}
