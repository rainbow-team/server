package com.rainbow.monitor.domain.extend;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.monitor.domain.WitnessMonitor;

/**
 * @Author:deepblue
 * @Date:2019/7/11 15:42
 * @Description:
 **/
public class WitnessMonitorExtend extends WitnessMonitor {

    // 核设施营运单位信息的名称
    @BeanFieldAnnotation(order = 1)
    public String serviceDepartName;

    // 铀矿冶单位信息的名称
    @BeanFieldAnnotation(order = 2)
    public String umineName;

    // 核设备单位的名称
    @BeanFieldAnnotation(order = 3)
    public String equipDepartName;

    public String getServiceDepartName() {
        return serviceDepartName;
    }

    public void setServiceDepartName(String serviceDepartName) {
        this.serviceDepartName = serviceDepartName;
    }

    public String getUmineName() {
        return umineName;
    }

    public void setUmineName(String umineName) {
        this.umineName = umineName;
    }

    public String getEquipDepartName() {
        return equipDepartName;
    }

    public void setEquipDepartName(String equipDepartName) {
        this.equipDepartName = equipDepartName;
    }
}
