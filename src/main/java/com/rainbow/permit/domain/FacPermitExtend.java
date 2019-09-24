package com.rainbow.permit.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;

/**
 * @Author:deepblue
 * @Date:2019/7/9 09:12
 * @Description:
 **/
public class FacPermitExtend extends FacPermit {

    // 营运单位的名称
    @BeanFieldAnnotation(order = 1)
    public String serviceDepartName;

    // 核设施信息的名称
    @BeanFieldAnnotation(order = 2)
    public String facName;

    // 核设施许可阶段的值
    @BeanFieldAnnotation(order = 3)
    public String permitStageValue;

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

    public String getPermitStageValue() {
        return permitStageValue;
    }

    public void setPermitStageValue(String permitStageValue) {
        this.permitStageValue = permitStageValue;
    }
}
