package com.rainbow.system.service.impl;

import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.system.dao.RoleMenuMapper;
import com.rainbow.system.domain.RoleMenu;
import com.rainbow.system.domain.SystemMenu;
import com.rainbow.system.domain.extend.RoleWithMenu;
import com.rainbow.system.service.RoleMenuServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:deepblue
 * @Date:2019/4/30 13:53
 * @Description:
 **/


@Service("roleMenuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleMenuServiceImpl  extends BaseService<RoleMenu> implements RoleMenuServie {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public void insetRoleMenuByRole(RoleWithMenu role) {
        if ((role.getRoleMenuList() != null) && (role.getRoleMenuList().size() > 0)) {
            for(SystemMenu systemMenu:role.getRoleMenuList()){
                RoleMenu roleMenu=new RoleMenu();
                roleMenu.setId(GuidHelper.getGuid());
                roleMenu.setRoleId(role.getId());
                roleMenu.setMenuId(systemMenu.getId());
                roleMenuMapper.insert(roleMenu);
            }
        }
    }

    @Override
    public void deleteRoleMenusByRoleId(String roleId) {
        roleMenuMapper.deleteRoleMenusByRoleId(roleId);
    }

    /*    @Override
    @Transactional
    public void deleteRoleMenusByRoleId(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(","));
        this.batchDelete(list, "roleId", RoleMenu.class);
    }*/
}
