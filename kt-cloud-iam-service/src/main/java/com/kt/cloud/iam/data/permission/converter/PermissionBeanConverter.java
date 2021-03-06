package com.kt.cloud.iam.data.permission.converter;

import com.kt.cloud.iam.dao.entity.IamPermission;
import com.kt.cloud.iam.data.permission.vo.PermissionVO;
import org.springframework.stereotype.Component;

/**
 * @title:
 * @desc:
 * @author: Javis
 */
@Component
public class PermissionBeanConverter {

    public PermissionVO convertToVO(IamPermission iamPermission) {
        PermissionVO vo = new PermissionVO();
        vo.setPermissionId(iamPermission.getId());
        return vo;
    }
}
