package com.rainbow.monitor.domain.extend;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.monitor.domain.DailyMonitor;

/**
 * @Author:deepblue
 * @Date:2019/7/11 13:51
 * @Description:
 **/
public class DailyMonitorExtend extends DailyMonitor {

    // 核设施运营单位的名称
    @BeanFieldAnnotation(order = 1)
    public String serviceDepartName;

    // 核设施的名称
    @BeanFieldAnnotation(order = 2)
    public String facName;

    // 核设施状态的值
    @BeanFieldAnnotation(order = 3)
    public String statusValue;

    // 核设施监管机构的值
    @BeanFieldAnnotation(order = 4)
    public String orgName;

    // 日常监督文件类型的值
    @BeanFieldAnnotation(order = 5)
    public String fileTypeValue;

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

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
