package com.rainbow.permit.domain;

import com.rainbow.unit.domain.UmineMountainExtend;

/**
 * @Author:deepblue
 * @Date:2019/7/10 10:33
 * @Description:
 **/
public class UmineMountainPermitExtend extends UmineMountainExtend {

    //铀矿冶单位名称
    private String umineName;

    //铀矿山名称
    private String umineMountainName;

    @Override
    public String getUmineName() {
        return umineName;
    }

    @Override
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
