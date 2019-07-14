package com.rainbow.system.domain.extend;

import com.rainbow.system.domain.SystemMenu;

import java.util.List;

public class SystemMenuExtend extends SystemMenu{

    //菜单的子菜单
    private List<SystemMenu> subSystemMenu;

    public List<SystemMenu> getSubSystemMenu() {
        return subSystemMenu;
    }

    public void setSubSystemMenu(List<SystemMenu> subSystemMenu) {
        this.subSystemMenu = subSystemMenu;
    }
}
