package com.rainbow.check.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/10 13:50
 * @Description:
 **/
public class FacCheckExtend extends FacCheck {

    //核设施营运单位名称
    private String serviceDepartName;

    //核设施名称
    private String facName;

    //核设施评审类型值
    private String typeValue;

    //核设施评审阶段的值
    private String stageValue;

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

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getStageValue() {
        return stageValue;
    }

    public void setStageValue(String stageValue) {
        this.stageValue = stageValue;
    }
}
