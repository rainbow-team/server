package com.rainbow.monitor.domain.extend;

import com.rainbow.unit.domain.FacReportExtend;

/**
 * @Author:deepblue
 * @Date:2019/7/11 16:19
 * @Description:
 **/
public class ReportMonitorExtend extends FacReportExtend {

    //授权监管机构的名称
    private String orgName;

    //监督报告类型的值
    private String reportTypeValue;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String getReportTypeValue() {
        return reportTypeValue;
    }

    @Override
    public void setReportTypeValue(String reportTypeValue) {
        this.reportTypeValue = reportTypeValue;
    }
}
