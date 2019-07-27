package com.rainbow.statistics.domain;

import java.util.Date;

public class SearchCondition {

    private String reportName;
    //需要查询的主表
    private String tableName;

    //主表中的查询字段
    private String propertyName;

    //查询字段说对应的的配置表的名称
    private String configTableName;

    private Date startDate;

    private Date endDate;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getConfigTableName() {
        return configTableName;
    }

    public void setConfigTableName(String configTableName) {
        this.configTableName = configTableName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
