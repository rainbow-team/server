package com.rainbow.config.domain;

import javax.persistence.*;

@Table(name = "link_detail")
public class LinkDetail {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 链接配置的名称
     */
    private String name;

    /**
     * 链接的地址
     */
    private String address;

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
     * 获取链接配置的名称
     *
     * @return name - 链接配置的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置链接配置的名称
     *
     * @param name 链接配置的名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取链接的地址
     *
     * @return address - 链接的地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置链接的地址
     *
     * @param address 链接的地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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