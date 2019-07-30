package com.rainbow.statistics.domain;

import java.util.Date;
import java.util.List;

public class SearchCondition {

    private String reportName;
    //需要查询的主表
    private String tableName;

    //主表中的查询字段
    private String propertyName;

    //查询字段说对应的的配置表的名称
    private String configTableName;

    //对应的数据归类的属性名称
    private String dateProperty;

    private Date startDate;

    private Date endDate;

    //是否是核设施单位 true false null
    private String isService;

    //需要查询主表的ID限定集合
    private String ids;

    private String idsProperty;

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

    public String getDateProperty() {
        return dateProperty;
    }

    public void setDateProperty(String dateProperty) {
        this.dateProperty = dateProperty;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getIdsProperty() {
        return idsProperty;
    }

    public void setIdsProperty(String idsProperty) {
        this.idsProperty = idsProperty;
    }

    public String getIsService() {
        return isService;
    }

    public void setIsService(String isService) {
        this.isService = isService;
    }
}
