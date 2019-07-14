package com.rainbow.system.domain.extend;

import com.rainbow.system.domain.Role;
import com.rainbow.system.domain.SystemMenu;

import java.util.List;

public class RoleWithMenu extends Role {

    private List<SystemMenu> roleMenuList;

    public List<SystemMenu> getRoleMenuList() {
        return roleMenuList;
    }

    public void setRoleMenuList(List<SystemMenu> roleMenuList) {
        this.roleMenuList = roleMenuList;
    }
}