package com.rainbow.monitor.domain.extend;

import com.rainbow.monitor.domain.CheckMonitor;

/**
 * @Author:deepblue
 * @Date:2019/7/11 14:51
 * @Description:
 **/
public class CheckMonitorExtend extends CheckMonitor {

    //核设施营运单位信息的名称
    private String serviceDepartName;

    //铀矿冶单位信息的名称
    private String umineName;

    //核设备单位的名称
    private String equipDepartName;

    //监督检查类型的值
    private String typeValue;

    //监督检查机构的名称
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getServiceDepartName() {
        return serviceDepartName;
    }

    public void setServiceDepartName(String serviceDepartName) {
        this.serviceDepartName = serviceDepartName;
    }

    public String getUmineName() {
        return umineName;
    }

    public void setUmineName(String umineName) {
        this.umineName = umineName;
    }

    public String getEquipDepartName() {
        return equipDepartName;
    }

    public void setEquipDepartName(String equipDepartName) {
        this.equipDepartName = equipDepartName;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
