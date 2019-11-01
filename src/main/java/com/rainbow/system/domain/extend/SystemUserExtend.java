package com.rainbow.system.domain.extend;

import com.rainbow.system.domain.SystemUser;

public class SystemUserExtend extends SystemUser {

    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}