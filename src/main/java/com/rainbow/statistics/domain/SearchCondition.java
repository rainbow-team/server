package com.rainbow.statistics.domain;

public class SearchCondition {
    private String tableName;

    private String propertyName;

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
