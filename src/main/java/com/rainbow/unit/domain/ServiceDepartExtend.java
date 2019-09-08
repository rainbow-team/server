package com.rainbow.unit.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;

/**
 * @Author:deepblue
 * @Date:2019/7/7 14:39
 * @Description:
 **/
public class ServiceDepartExtend extends ServiceDepart {

    //所属集团的名称
    @BeanFieldAnnotation(order = 2)
    public String groupName;

    //核设施的数量
    public int facNum;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getFacNum() {
        return facNum;
    }

    public void setFacNum(int facNum) {
        this.facNum = facNum;
    }
}
