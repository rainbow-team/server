package com.rainbow.security.domain.extend;

import com.rainbow.security.domain.AccidentSecurity;

/**
 * @Author:deepblue
 * @Date:2019/7/12 17:13
 * @Description:
 **/
public class AccidentSecurityExtend extends AccidentSecurity {

    //核设施营运单位的名称
    private String serviceDepartName;

    //铀矿冶单位的名称
    private String umineName;

    //核设施信息的名称
    private String facName;

    //铀尾矿(渣)库信息的名称
    private String uminePlaceName;

    //核设施状态的值
    private String facStatusValue;

    //铀尾矿(渣)库的状态的值
    private String uminePlaceStatusValue;

    //事故事件的类别的值
    private String typeValue;

    //事故事件性质的值
    private String natureValue;

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

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getUminePlaceName() {
        return uminePlaceName;
    }

    public void setUminePlaceName(String uminePlaceName) {
        this.uminePlaceName = uminePlaceName;
    }

    public String getFacStatusValue() {
        return facStatusValue;
    }

    public void setFacStatusValue(String facStatusValue) {
        this.facStatusValue = facStatusValue;
    }

    public String getUminePlaceStatusValue() {
        return uminePlaceStatusValue;
    }

    public void setUminePlaceStatusValue(String uminePlaceStatusValue) {
        this.uminePlaceStatusValue = uminePlaceStatusValue;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getNatureValue() {
        return natureValue;
    }

    public void setNatureValue(String natureValue) {
        this.natureValue = natureValue;
    }
}
