package com.rainbow.config.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "system_log")
public class SystemLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户外键
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 操作时间
     */
    @Column(name = "oper_time")
    private Date operTime;

    /**
     * 操作内容
     */
    @Column(name = "oper_content")
    private String operContent;

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
     * 获取用户外键
     *
     * @return user_id - 用户外键
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户外键
     *
     * @param userId 用户外键
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取操作时间
     *
     * @return oper_time - 操作时间
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * 设置操作时间
     *
     * @param operTime 操作时间
     */
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    /**
     * 获取操作内容
     *
     * @return oper_content - 操作内容
     */
    public String getOperContent() {
        return operContent;
    }

    /**
     * 设置操作内容
     *
     * @param operContent 操作内容
     */
    public void setOperContent(String operContent) {
        this.operContent = operContent == null ? null : operContent.trim();
    }
}