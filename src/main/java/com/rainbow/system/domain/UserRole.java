package com.rainbow.system.domain;

import javax.persistence.*;

@Table(name = "system_user_role")
public class UserRole {
    /**
     * 主键ID
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户外键ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 角色外键ID
     */
    @Column(name = "role_id")
    private String roleId;

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
     * 获取用户外键ID
     *
     * @return user_id - 用户外键ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户外键ID
     *
     * @param userId 用户外键ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取角色外键ID
     *
     * @return role_id - 角色外键ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色外键ID
     *
     * @param roleId 角色外键ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}