package com.kt.upms.api.controller;


import cn.hutool.extra.cglib.CglibUtil;
import com.kt.component.dto.PageRequest;
import com.kt.component.dto.ServerResponse;
import com.kt.model.dto.UserGroupAddDTO;
import com.kt.model.dto.UserGroupQueryDTO;
import com.kt.model.dto.UserGroupUpdateDTO;
import com.kt.upms.entity.UpmsUserGroup;
import com.kt.upms.service.IUpmsUserGroupService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 用户组表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/user-group")
public class UpmsUserGroupController extends BaseController {

    private final IUpmsUserGroupService iUpmsUserGroupService;

    public UpmsUserGroupController(IUpmsUserGroupService iUpmsUserGroupService) {
        this.iUpmsUserGroupService = iUpmsUserGroupService;
    }

    @PostMapping("/list")
    public ServerResponse list(@RequestBody PageRequest<UserGroupQueryDTO> pageRequest) {
        return ServerResponse.ok(iUpmsUserGroupService.pageList(getPage(pageRequest), pageRequest.getParams()));
    }

    @PostMapping("/add")
    public ServerResponse add(@RequestBody @Validated UserGroupAddDTO userGroupAddDTO) {
        return ServerResponse.ok(iUpmsUserGroupService.save(userGroupAddDTO));
    }

    @PostMapping("/update")
    public ServerResponse update(@RequestBody @Validated UserGroupUpdateDTO userGroupUpdateDTO) {
        UpmsUserGroup upmsUserGroup = CglibUtil.copy(userGroupUpdateDTO, UpmsUserGroup.class);
        iUpmsUserGroupService.updateById(upmsUserGroup);
        return ServerResponse.ok();
    }

    @GetMapping("/{id}")
    public ServerResponse get(@PathVariable("id") String userGroupId) {
        UpmsUserGroup upmsUserGroup = iUpmsUserGroupService.getById(userGroupId);
        return ServerResponse.ok(CglibUtil.copy(upmsUserGroup, UserGroupQueryDTO.class));
    }
}

