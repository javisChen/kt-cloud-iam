package com.kt.upms.module.api.converter;

import com.kt.upms.module.api.persistence.UpmsApi;
import com.kt.upms.module.api.persistence.UpmsApiCategory;
import com.kt.upms.module.permission.persistence.UpmsPermission;
import com.kt.upms.enums.PermissionTypeEnums;
import com.kt.upms.module.api.dto.ApiCategoryUpdateDTO;
import com.kt.upms.module.api.dto.ApiUpdateDTO;
import com.kt.upms.module.api.vo.ApiCategoryBaseVO;
import com.kt.upms.module.api.vo.ApiListVO;
import com.kt.upms.module.permission.service.IPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title:
 * @desc:
 * @author: Javis
 */
@Component
public class ApiBeanConverter {

    @Autowired
    private IPermissionService iPermissionService;

    public UpmsApi convertForUpdate(ApiUpdateDTO dto) {
        UpmsApi upmsApi = new UpmsApi();
        upmsApi.setId(dto.getId());
        upmsApi.setName(dto.getName());
        upmsApi.setApplicationId(dto.getApplicationId());
        upmsApi.setUrl(dto.getUrl());
        upmsApi.setMethod(dto.getMethod());
        upmsApi.setAuthType(dto.getAuthType());
        upmsApi.setStatus(dto.getStatus());
        upmsApi.setCategoryId(dto.getCategoryId());
        // 约定规则，如果url包含了*号就代表是包含了路径参数
        upmsApi.setHasPathVariable(StringUtils.contains(dto.getUrl(), "*"));
        return upmsApi;
    }

    public ApiListVO convertToApiListVO(UpmsApi upmsApi) {
        UpmsPermission permission = iPermissionService.getPermission(upmsApi.getId(), PermissionTypeEnums.SER_API);
        ApiListVO apiListVO = new ApiListVO();
        apiListVO.setId(upmsApi.getId());
        apiListVO.setName(upmsApi.getName());
        apiListVO.setApplicationId(upmsApi.getApplicationId());
        apiListVO.setUrl(upmsApi.getUrl());
        apiListVO.setMethod(upmsApi.getMethod());
        apiListVO.setAuthType(upmsApi.getAuthType());
        apiListVO.setStatus(upmsApi.getStatus());
        apiListVO.setPermissionId(permission.getId());
        apiListVO.setPermissionCode(permission.getCode());
        apiListVO.setCreateTime(upmsApi.getGmtCreate());
        apiListVO.setUpdateTime(upmsApi.getGmtModified());
        return apiListVO;
    }

    public ApiCategoryBaseVO convertToApiCategoryVO(UpmsApiCategory category) {
        ApiCategoryBaseVO apiCategoryBaseVO = new ApiCategoryBaseVO();
        apiCategoryBaseVO.setId(category.getId());
        apiCategoryBaseVO.setName(category.getName());
        apiCategoryBaseVO.setApplicationId(category.getApplicationId());
        return apiCategoryBaseVO;
    }

    public UpmsApiCategory convertToDO(ApiCategoryUpdateDTO dto) {
        UpmsApiCategory upmsApiCategory = new UpmsApiCategory();
        upmsApiCategory.setId(dto.getId());
        upmsApiCategory.setName(dto.getName());
        upmsApiCategory.setApplicationId(dto.getApplicationId());
        return upmsApiCategory;
    }
}