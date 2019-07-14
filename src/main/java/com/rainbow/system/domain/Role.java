package com.rainbow.system.domain;

import javax.persistence.*;

@Table(name = "system_role")
public class Role {
    /**
     * 角色主键ID
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 角色名称
     */
    private String name;

    private String note;

    /**
     * 获取角色主键ID
     *
     * @return id - 角色主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置角色主键ID
     *
     * @param id 角色主键ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}