package com.kt.iam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum PageStatusEnums {

    ENABLED(1, "已启用"),
    DISABLED(2, "已禁用");

    PageStatusEnums(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    private int value;
    private String desc;

    public int getValue() {
        return value;
    }
}