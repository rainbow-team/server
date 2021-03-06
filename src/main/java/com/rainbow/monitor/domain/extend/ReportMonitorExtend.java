package com.rainbow.monitor.domain.extend;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.monitor.domain.ReportMonitor;
import com.rainbow.unit.domain.FacReportExtend;

/**
 * @Author:deepblue
 * @Date:2019/7/11 16:19
 * @Description:
 **/
public class ReportMonitorExtend extends ReportMonitor {

    // 授权监管机构的名称
    @BeanFieldAnnotation(order = 1)
    public String orgName;

    // 监督报告类型的值
    @BeanFieldAnnotation(order = 3)
    public String reportTypeValue;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getReportTypeValue() {
        return reportTypeValue;
    }

    public void setReportTypeValue(String reportTypeValue) {
        this.reportTypeValue = reportTypeValue;
    }
}
