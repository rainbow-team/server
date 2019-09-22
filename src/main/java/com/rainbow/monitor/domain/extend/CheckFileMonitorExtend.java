package com.rainbow.monitor.domain.extend;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.monitor.domain.CheckFileMonitor;

/**
 * @Author:deepblue
 * @Date:2019/7/11 14:51
 * @Description:
 **/
public class CheckFileMonitorExtend extends CheckFileMonitor {

    // 监督检查文件类型的值
    @BeanFieldAnnotation(order = 2)
    public String typeValue;

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
