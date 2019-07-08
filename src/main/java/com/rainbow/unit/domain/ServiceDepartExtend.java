package com.rainbow.unit.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/7 14:39
 * @Description:
 **/
public class ServiceDepartExtend extends ServiceDepart {

    //所属集团的名称
    private String groupName;

    //核设施的数量
    private int facNum;

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
