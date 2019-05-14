package com.rainbow.system.domain;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/4/30 09:07
 * @Description:
 **/
public class UserWithRole extends User {

    private Long roleId;

    private List<Long> roleIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
