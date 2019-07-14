package com.rainbow.system.domain;

import javax.persistence.*;

@Table(name = "system_menu")
public class SystemMenu {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 功能代码
     */
    private String code;

    /**
     * 功能名称
     */
    private String name;

    /**
     * 权限的父级ID，构建权限树
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 所在层级
     */
    @Column(name = "menu_level")
    private Integer menuLevel;

    /**
     * 层级中的顺序
     */
    @Column(name = "menu_order")
    private Integer menuOrder;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取功能代码
     *
     * @return code - 功能代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置功能代码
     *
     * @param code 功能代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取功能名称
     *
     * @return name - 功能名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置功能名称
     *
     * @param name 功能名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取权限的父级ID，构建权限树
     *
     * @return parent_id - 权限的父级ID，构建权限树
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置权限的父级ID，构建权限树
     *
     * @param parentId 权限的父级ID，构建权限树
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取所在层级
     *
     * @return menu_level - 所在层级
     */
    public Integer getMenuLevel() {
        return menuLevel;
    }

    /**
     * 设置所在层级
     *
     * @param menuLevel 所在层级
     */
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    /**
     * 获取层级中的顺序
     *
     * @return menu_order - 层级中的顺序
     */
    public Integer getMenuOrder() {
        return menuOrder;
    }

    /**
     * 设置层级中的顺序
     *
     * @param menuOrder 层级中的顺序
     */
    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }
}