package com.kt.cloud.iam.data.api.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kt.cloud.iam.common.util.Assert;
import com.kt.cloud.iam.dao.entity.IamApiCategory;
import com.kt.cloud.iam.dao.mapper.IamApiCategoryMapper;
import com.kt.cloud.iam.data.api.converter.ApiBeanConverter;
import com.kt.cloud.iam.enums.BizEnums;
import com.kt.cloud.iam.enums.DeletedEnums;
import com.kt.cloud.iam.data.api.dto.ApiCategoryUpdateDTO;
import com.kt.cloud.iam.data.api.vo.ApiCategoryBaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @title:
 * @desc:
 * @author: Javis
 */
@Service
public class ApiCategoryServiceImpl extends ServiceImpl<IamApiCategoryMapper, IamApiCategory>
        implements IApiCategoryService {

    @Autowired
    private ApiBeanConverter beanConverter;
    @Autowired
    private IApiService iApiService;

    @Override
    public List<ApiCategoryBaseVO> listVos(Long applicationId) {
        LambdaQueryWrapper<IamApiCategory> qw = new LambdaQueryWrapper<>();
        qw.eq(IamApiCategory::getIsDeleted, DeletedEnums.NOT.getCode());
        qw.eq(IamApiCategory::getApplicationId, applicationId);
        return this.list(qw).stream().map(beanConverter::convertToApiCategoryVO).collect(Collectors.toList());
    }

    @Override
    public void saveApiCategory(ApiCategoryUpdateDTO dto) {
        IamApiCategory one = getApiCategoryByNameAndApplicationId(dto);
        Assert.isTrue(one != null, BizEnums.API_CATEGORY_ALREADY_EXISTS);
        IamApiCategory apiCategory = beanConverter.convertToDO(dto);
        this.save(apiCategory);
    }

    private IamApiCategory getApiCategoryByNameAndApplicationId(ApiCategoryUpdateDTO dto) {
        LambdaQueryWrapper<IamApiCategory> qw = new LambdaQueryWrapper<>();
        qw.eq(IamApiCategory::getIsDeleted, DeletedEnums.NOT.getCode());
        qw.eq(IamApiCategory::getName, dto.getName());
        qw.eq(IamApiCategory::getApplicationId, dto.getApplicationId());
        return this.getOne(qw);
    }

    @Override
    public void updateApiCategory(ApiCategoryUpdateDTO dto) {
        IamApiCategory one = getApiCategoryByNameAndApplicationId(dto);
        Assert.isTrue(one != null && !dto.getId().equals(one.getId()), BizEnums.API_CATEGORY_ALREADY_EXISTS);
        IamApiCategory apiCategory = beanConverter.convertToDO(dto);
        this.updateById(apiCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeApiCategory(Long id) {
        LambdaUpdateWrapper<IamApiCategory> qw = new LambdaUpdateWrapper<>();
        qw.eq(IamApiCategory::getId, id);
        qw.eq(IamApiCategory::getIsDeleted, DeletedEnums.NOT.getCode());
        qw.set(IamApiCategory::getIsDeleted, id);
        this.update(qw);

        // ???????????????api????????????
        iApiService.removeByCategoryId(id);
    }
}
