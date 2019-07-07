package com.rainbow.supervision.controller.domain;

import com.rainbow.supervision.domain.SupervisionSupervisor;

/**
 * Created by 13260 on 2019/7/6.
 */
public class SupervisionSupervisorResponse extends SupervisionSupervisor{

    public String orgName;

    public String titleName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
}
