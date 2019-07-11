package com.rainbow.check.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/11 10:15
 * @Description:
 **/
public class UminePlaceCheckExtend extends UminePlaceCheck {

    //铀矿冶单位信息名称
    private String umineName;

    //铀尾矿(渣) 库信息名称
    private String uminePlaceName;

    //铀尾矿(渣)库审评信息的值
    private String typeValue;

    //铀尾矿(渣)库审评阶段的值
    private String stageValue;

    public String getUmineName() {
        return umineName;
    }

    public void setUmineName(String umineName) {
        this.umineName = umineName;
    }

    public String getUminePlaceName() {
        return uminePlaceName;
    }

    public void setUminePlaceName(String uminePlaceName) {
        this.uminePlaceName = uminePlaceName;
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
