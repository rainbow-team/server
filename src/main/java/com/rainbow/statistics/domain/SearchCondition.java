package com.rainbow.statistics.domain;

public class SearchCondition {

    //需要查询的主表
    private String tableName;

    //主表中的查询字段
    private String propertyName;


    //查询字段说对应的的配置表的名称
    private String configTableName;

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
}
