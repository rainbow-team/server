package com.rainbow.permit.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/10 10:04
 * @Description:
 **/
public class UminePlacePermitExtend extends UminePlacePermit {

    //铀矿冶单位名称
    private String umineName;

    //铀尾矿(渣)库名称
    private String uminePlaceName;

    //铀尾矿(渣)库许可阶段的值
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

    public String getStageValue() {
        return stageValue;
    }

    public void setStageValue(String stageValue) {
        this.stageValue = stageValue;
    }
}
