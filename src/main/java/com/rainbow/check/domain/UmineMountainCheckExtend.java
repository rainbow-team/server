package com.rainbow.check.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/11 10:46
 * @Description:
 **/
public class UmineMountainCheckExtend extends UmineMountainCheck {

    //铀矿冶单位信息名称
    private String umineName;

    //铀矿山信息名称
    private String umineMountainName;

    public String getUmineName() {
        return umineName;
    }

    public void setUmineName(String umineName) {
        this.umineName = umineName;
    }

    public String getUmineMountainName() {
        return umineMountainName;
    }

    public void setUmineMountainName(String umineMountainName) {
        this.umineMountainName = umineMountainName;
    }
}
