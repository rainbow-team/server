package com.rainbow.system.service;

import com.rainbow.common.service.IService;
import com.rainbow.system.domain.RoleMenu;
import com.rainbow.system.domain.extend.RoleWithMenu;

/**
 * @Author:deepblue
 * @Date:2019/4/30 13:52
 * @Description:
 **/
public interface RoleMenuServie extends IService<RoleMenu> {

    void insetRoleMenuByRole(RoleWithMenu role);

    void deleteRoleMenusByRoleId(String roleId);

}
