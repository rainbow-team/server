package com.rainbow.unit.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "unit_umine_mountain_improve")
public class UmineMountainImprove {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 铀矿山ID，关联表unit_umine_mountain
     */
    @Column(name = "umine_mountain_id")
    private String umineMountainId;

    /**
     * 安技改时间
     */
    @Column(name = "improve_date")
    private Date improveDate;

    /**
     * 安技改内容
     */
    @Column(name = "improve_content")
    private String improveContent;

    @Transient
    private String umineMountainName;
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
     * 获取铀矿山ID，关联表unit_umine_mountain
     *
     * @return umine_mountain_id - 铀矿山ID，关联表unit_umine_mountain
     */
    public String getUmineMountainId() {
        return umineMountainId;
    }

    /**
     * 设置铀矿山ID，关联表unit_umine_mountain
     *
     * @param umineMountainId 铀矿山ID，关联表unit_umine_mountain
     */
    public void setUmineMountainId(String umineMountainId) {
        this.umineMountainId = umineMountainId == null ? null : umineMountainId.trim();
    }

    /**
     * 获取安技改时间
     *
     * @return improve_date - 安技改时间
     */
    public Date getImproveDate() {
        return improveDate;
    }

    /**
     * 设置安技改时间
     *
     * @param improveDate 安技改时间
     */
    public void setImproveDate(Date improveDate) {
        this.improveDate = improveDate;
    }

    /**
     * 获取安技改内容
     *
     * @return improve_content - 安技改内容
     */
    public String getImproveContent() {
        return improveContent;
    }

    /**
     * 设置安技改内容
     *
     * @param improveContent 安技改内容
     */
    public void setImproveContent(String improveContent) {
        this.improveContent = improveContent == null ? null : improveContent.trim();
    }

    public String getUmineMountainName() {
        return umineMountainName;
    }

    public void setUmineMountainName(String umineMountainName) {
        this.umineMountainName = umineMountainName;
    }
}