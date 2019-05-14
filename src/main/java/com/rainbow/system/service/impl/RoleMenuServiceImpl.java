package com.rainbow.system.service.impl;

import com.rainbow.common.service.impl.BaseService;
import com.rainbow.system.domain.RoleMenu;
import com.rainbow.system.service.RoleMenuServie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/4/30 13:53
 * @Description:
 **/


@Service("roleMenuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleMenuServiceImpl  extends BaseService<RoleMenu> implements RoleMenuServie {
    @Override
    @Transactional
    public void deleteRoleMenusByRoleId(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(","));
        this.batchDelete(list, "roleId", RoleMenu.class);
    }

    @Override
    @Transactional
    public void deleteRoleMenusByMenuId(String menuIds) {
        List<String> list = Arrays.asList(menuIds.split(","));
        this.batchDelete(list, "menuId", RoleMenu.class);
    }
}
