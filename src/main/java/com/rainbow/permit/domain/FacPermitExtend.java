package com.rainbow.permit.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/9 09:12
 * @Description:
 **/
public class FacPermitExtend extends FacPermit {

    //营运单位的名称
    private String serviceDepartValue;

    //核设施信息的名称
    private String facValue;

    //核设施许可阶段的值
    private String permitStageValue;

    public String getServiceDepartValue() {
        return serviceDepartValue;
    }

    public void setServiceDepartValue(String serviceDepartValue) {
        this.serviceDepartValue = serviceDepartValue;
    }

    public String getFacValue() {
        return facValue;
    }

    public void setFacValue(String facValue) {
        this.facValue = facValue;
    }

    public String getPermitStageValue() {
        return permitStageValue;
    }

    public void setPermitStageValue(String permitStageValue) {
        this.permitStageValue = permitStageValue;
    }
}
