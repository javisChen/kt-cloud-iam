package com.kt.cloud.iam.dao.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.kt.component.orm.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色与权限关联表
 * </p>
 *
 * @author
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IamPermissionRoleRel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id，关联iam_permission.id
     */
    @TableField("permission_id")
    private Long permissionId;

    /**
     * 角色id，关联iam_role.id
     */
    @TableField("role_id")
    private Long roleId;


}
