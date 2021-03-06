package com.kt.cloud.iam.dao.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.kt.component.orm.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户组与用户关联表
 * </p>
 *
 * @author
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IamUserGroupUserRel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组名称，关联iam_user_group.id
     */
    @TableField("user_group_id")
    private Long userGroupId;

    /**
     * 用户id，关联iam_user_id
     */
    @TableField("user_id")
    private Long userId;


}
