package com.rainbow.monitor.domain.extend;

import com.rainbow.monitor.domain.DailyMonitor;

/**
 * @Author:deepblue
 * @Date:2019/7/11 13:51
 * @Description:
 **/
public class DailyMonitorExtend extends DailyMonitor {

    //核设施运营单位的名称
    private String serviceDepartName;

    //核设施的名称
    private String facName;

    //核设施状态的值
    private String stageVaue;

    //核设施监管机构的值
    private String orgName;

    //日常监督文件类型的值
    private String fileTypeValue;

    public String getFileTypeValue() {
        return fileTypeValue;
    }

    public void setFileTypeValue(String fileTypeValue) {
        this.fileTypeValue = fileTypeValue;
    }

    public String getServiceDepartName() {
        return serviceDepartName;
    }

    public void setServiceDepartName(String serviceDepartName) {
        this.serviceDepartName = serviceDepartName;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getStageVaue() {
        return stageVaue;
    }

    public void setStageVaue(String stageVaue) {
        this.stageVaue = stageVaue;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
