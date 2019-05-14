package com.rainbow.system.domain;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/4/30 13:39
 * @Description:
 **/
public class RoleWithMenu extends Role{

    private Long menuId;

    private List<Long> menuIds;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
