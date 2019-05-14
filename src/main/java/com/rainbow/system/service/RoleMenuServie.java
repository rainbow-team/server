package com.rainbow.system.service;

import com.rainbow.common.service.IService;
import com.rainbow.system.domain.RoleMenu;

/**
 * @Author:deepblue
 * @Date:2019/4/30 13:52
 * @Description:
 **/
public interface RoleMenuServie extends IService<RoleMenu> {

    void deleteRoleMenusByRoleId(String roleIds);

    void deleteRoleMenusByMenuId(String menuIds);
}
