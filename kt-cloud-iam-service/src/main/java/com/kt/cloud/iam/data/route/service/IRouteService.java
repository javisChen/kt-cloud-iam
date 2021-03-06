package com.kt.cloud.iam.data.route.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kt.cloud.iam.dao.entity.IamRoute;
import com.kt.cloud.iam.data.route.dto.RouteModifyParentDTO;
import com.kt.cloud.iam.data.route.dto.RouteQueryDTO;
import com.kt.cloud.iam.data.route.dto.RouteUpdateDTO;
import com.kt.cloud.iam.data.user.vo.UserPermissionRouteNavVO;
import com.kt.cloud.iam.data.route.vo.RouteDetailVO;
import com.kt.cloud.iam.data.route.vo.RouteElementVO;
import com.kt.cloud.iam.data.route.vo.RouteListTreeVO;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author
 * @since 2020-11-09
 */
public interface IRouteService extends IService<IamRoute> {

    Page<RouteListTreeVO> pageList(RouteQueryDTO pageRequest);

    void saveRoute(RouteUpdateDTO dto);

    void updateRoute(RouteUpdateDTO dto);

    void updateRouteStatus(RouteUpdateDTO dto);

    void modifyParent(RouteModifyParentDTO dto);

    RouteDetailVO getRoute(Long id);

    void deleteRouteById(Long id);

    List<RouteListTreeVO> listAllVOs(RouteQueryDTO dto);

    List<RouteElementVO> listRouteElementsById(Long routeId);

    String getRouteNameById(Long pid);

    long countByApplicationId(Long applicationId);

    List<UserPermissionRouteNavVO> getRouteVOSByIds(List<Long> routeIds);
}
