package com.kt.cloud.iam.data.user.converter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.kt.cloud.iam.dao.entity.IamPermission;
import com.kt.cloud.iam.dao.entity.IamUser;
import com.kt.cloud.iam.data.permission.vo.PermissionVO;
import com.kt.cloud.iam.data.role.service.IRoleService;
import com.kt.cloud.iam.data.user.dto.UserUpdateDTO;
import com.kt.cloud.iam.data.user.service.IUserService;
import com.kt.cloud.iam.data.user.support.IUserPasswordHelper;
import com.kt.cloud.iam.data.user.vo.UserDetailVO;
import com.kt.cloud.iam.data.user.vo.UserPageListVO;
import com.kt.cloud.iam.data.usergroup.service.IUserGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @title:
 * @desc:
 * @author: Javis
 */
@Component
public class UserBeanConverter {

    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserGroupService iUserGroupService;
    @Autowired
    private IUserPasswordHelper iUserPasswordHelper;
    @Autowired
    private IUserService iUserService;

    public UserPageListVO convertToUserPageListVO(IamUser iamUser) {
        Long userId = iamUser.getId();
        List<String> roles = iRoleService.getRoleNamesByUserId(userId);
        List<String> userGroups = iUserGroupService.getUserGroupNamesByUserId(userId);
        UserPageListVO userListVO = new UserPageListVO();
        userListVO.setId(iamUser.getId());
        userListVO.setPhone(iamUser.getPhone());
        userListVO.setName(iamUser.getName());
        userListVO.setStatus(iamUser.getStatus());
        userListVO.setRoles(roles);
        userListVO.setUserGroups(userGroups);
        return userListVO;
    }

    public UserDetailVO convertToUserDetailVO(IamUser user) {
        UserDetailVO vo = new UserDetailVO();
        Long userId = user.getId();
        vo.setId(userId);
        vo.setPhone(user.getPhone());
        vo.setName(user.getName());
        vo.setStatus(user.getStatus());
        vo.setRoleIds(iRoleService.getRoleIdsByUserId(userId));
        vo.setUserGroupIds(iUserGroupService.getUserGroupIdsByUserId(userId));
        return vo;
    }

    public PermissionVO convertToPermissionVO(IamPermission permission) {
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setPermissionCode(permission.getCode());
        return permissionVO;
    }

    public IamUser convertToUserDO(UserUpdateDTO dto) {
        IamUser iamUser = new IamUser();
        iamUser.setId(dto.getId());
        iamUser.setName(dto.getName());
        iamUser.setPhone(dto.getPhone());
        iamUser.setPassword(dto.getPassword());
        iamUser.setStatus(dto.getStatus());
        String code = generateUserCode();
        iamUser.setCode(code);
        // ????????????=bcrypt(??????md5(md5(password)) + salt)
        iamUser.setPassword(iUserPasswordHelper.enhancePassword(DigestUtil.md5Hex(iamUser.getPassword())));
        return iamUser;
    }

    public IamUser convertToUpdateUserDO(UserUpdateDTO dto) {
        IamUser iamUser = new IamUser();
        iamUser.setId(dto.getId());
        iamUser.setName(dto.getName());
        iamUser.setPhone(dto.getPhone());
        iamUser.setPassword(dto.getPassword());
        iamUser.setStatus(dto.getStatus());
        iamUser.setPassword(iUserPasswordHelper.enhancePassword(DigestUtil.md5Hex(iamUser.getPassword())));
        return iamUser;
    }

    private String generateUserCode() {
        // ???????????????????????????????????????????????????code???????????????????????????
        String code;
        do {
            code = StringUtils.remove(StrUtil.uuid(), "-");
        } while (codeExists(code));
        return code;
    }

    private boolean codeExists(String code) {
        return iUserService.countUserByCode(code) > 0;
    }
}
