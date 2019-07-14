package com.rainbow.system.domain.extend;

import com.rainbow.system.domain.Role;
import com.rainbow.system.domain.SystemUser;

import java.util.List;

public class UserWithRole extends SystemUser {

    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
