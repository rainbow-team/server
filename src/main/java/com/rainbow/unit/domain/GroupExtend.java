package com.rainbow.unit.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/7 13:24
 * @Description:
 **/
public class GroupExtend extends Group{

    //核设施营运单位数量
    private int serviceDepartNum;

    //铀矿冶单位
    private int umineNum;


    public int getServiceDepartNum() {
        return serviceDepartNum;
    }

    public void setServiceDepartNum(int serviceDepartNum) {
        this.serviceDepartNum = serviceDepartNum;
    }

    public int getUmineNum() {
        return umineNum;
    }

    public void setUmineNum(int umineNum) {
        this.umineNum = umineNum;
    }
}