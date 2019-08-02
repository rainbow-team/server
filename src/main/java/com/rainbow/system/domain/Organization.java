package com.rainbow.system.domain;

import javax.persistence.*;

@Table(name = "system_organization")
public class Organization {
    /**
     * 组织主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 单位编码
     */
    @Column(name = "code")
    private String code;

    /**
     * 组织名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 上级组织ID
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 类别层级
     */
    @Column(name = "org_lever")
    private Integer orgLever;

    /**
     * 层级中的顺序
     */
    @Column(name = "org_lever_order")
    private Integer orgLeverOrder;

    /**
     * 获取组织主键
     *
     * @return id - 组织主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置组织主键
     *
     * @param id 组织主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取单位编码
     *
     * @return code - 单位编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置单位编码
     *
     * @param code 单位编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取组织名称
     *
     * @return name - 组织名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组织名称
     *
     * @param name 组织名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取上级组织ID
     *
     * @return parent_id - 上级组织ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置上级组织ID
     *
     * @param parentId 上级组织ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取类别层级
     *
     * @return org_lever - 类别层级
     */
    public Integer getOrgLever() {
        return orgLever;
    }

    /**
     * 设置类别层级
     *
     * @param orgLever 类别层级
     */
    public void setOrgLever(Integer orgLever) {
        this.orgLever = orgLever;
    }

    /**
     * 获取层级中的顺序
     *
     * @return org_lever_order - 层级中的顺序
     */
    public Integer getOrgLeverOrder() {
        return orgLeverOrder;
    }

    /**
     * 设置层级中的顺序
     *
     * @param orgLeverOrder 层级中的顺序
     */
    public void setOrgLeverOrder(Integer orgLeverOrder) {
        this.orgLeverOrder = orgLeverOrder;
    }
}