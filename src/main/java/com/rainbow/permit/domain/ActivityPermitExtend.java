package com.rainbow.permit.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/9 15:39
 * @Description:
 **/
public class ActivityPermitExtend extends ActivityPermit {
    //营运单位的名称
    private String serviceDepartName;

    //核设备单位信息名称
    private String equipDepartName;

    //核设施信息的名称
    private String facName;

    //核活动类型的值
    private String typeValue;

    public String getServiceDepartName() {
        return serviceDepartName;
    }

    public void setServiceDepartName(String serviceDepartName) {
        this.serviceDepartName = serviceDepartName;
    }

    public String getEquipDepartName() {
        return equipDepartName;
    }

    public void setEquipDepartName(String equipDepartName) {
        this.equipDepartName = equipDepartName;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
