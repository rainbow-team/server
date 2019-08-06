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

    //井下消防审查备案情况的值
    private String recordValue;

    //井下消防验收情况的值
    private String acceptValue;

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

    public String getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(String recordValue) {
        this.recordValue = recordValue;
    }

    public String getAcceptValue() {
        return acceptValue;
    }

    public void setAcceptValue(String acceptValue) {
        this.acceptValue = acceptValue;
    }
}
