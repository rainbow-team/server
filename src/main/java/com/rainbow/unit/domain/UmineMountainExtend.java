package com.rainbow.unit.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/7 20:58
 * @Description:
 **/
public class UmineMountainExtend extends UmineMountain {

    //铀矿冶单位名称
    private String umineName;

    //铀矿山设施状态值
    private String statusValue;

    public String getUmineName() {
        return umineName;
    }

    public void setUmineName(String umineName) {
        this.umineName = umineName;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
}
