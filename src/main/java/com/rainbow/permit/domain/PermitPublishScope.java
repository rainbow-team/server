package com.rainbow.permit.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "permit_publish_scope")
public class PermitPublishScope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "permit_id")
    private String permitId;

    @Column(name = "user_id")
    private String userId;

    /**
     * 主键
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 许可信息的id
     */
    public String getPermitId() {
        return permitId;
    }

    public void setPermitId(String permitId) {
        this.permitId = permitId;
    }

    /**
     * 知悉用户的id
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}