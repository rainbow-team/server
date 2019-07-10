package com.rainbow.check.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/10 13:25
 * @Description:
 **/
public class FacFileCheckExtend extends  FacFileCheck {

    //核设施审评文件类型
    private String facCheckFileTypeValue;

    public String getFacCheckFileTypeValue() {
        return facCheckFileTypeValue;
    }

    public void setFacCheckFileTypeValue(String facCheckFileTypeValue) {
        this.facCheckFileTypeValue = facCheckFileTypeValue;
    }
}
