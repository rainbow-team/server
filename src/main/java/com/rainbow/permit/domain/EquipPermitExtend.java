package com.rainbow.permit.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/9 11:57
 * @Description:
 **/
public class EquipPermitExtend extends EquipPermit {

    //核设备单位信息名称
    private String equipDepartName;

    //营运单位的名称
    private String serviceDepartValue;

    //核设施信息的名称
    private String facValue;

    //设备类别的值
    private String typeValue;

    //核安全级别的值
    private String levelValue;

    //许可阶段的值
    private String stageValue;

    public String getEquipDepartName() {
        return equipDepartName;
    }

    public void setEquipDepartName(String equipDepartName) {
        this.equipDepartName = equipDepartName;
    }

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

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getStageValue() {
        return stageValue;
    }

    public void setStageValue(String stageValue) {
        this.stageValue = stageValue;
    }
}
