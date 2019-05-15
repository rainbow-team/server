package com.rainbow.config.domain;

import javax.persistence.*;
import java.io.Serializable;


public class SystemConfig  implements Serializable{
    /**
     * 主键
     */
    private String id;

    /**
     * 配置的值
     */
    private String value;

    /**
     * 排序序号
     */
    private Integer order;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 配置的值
     *
     * @return value - 配置的值
     */
    public String getValue() {
        return value;
    }

    /**
     * 配置的值
     *
     * @param value 配置的值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 获取排序序号
     *
     * @return order - 排序序号
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 设置排序序号
     *
     * @param order 排序序号
     */
    public void setOrder(Integer order) {
        this.order = order;
    }
}