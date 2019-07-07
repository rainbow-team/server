package com.rainbow.unit.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "unit_fac_improve")
public class FacImprove {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核设施外键ID，关联表unit_fac
     */
    @Column(name = "fac_id")
    private String facId;

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
     * 获取核设施外键ID，关联表unit_fac
     *
     * @return fac_id - 核设施外键ID，关联表unit_fac
     */
    public String getFacId() {
        return facId;
    }

    /**
     * 设置核设施外键ID，关联表unit_fac
     *
     * @param facId 核设施外键ID，关联表unit_fac
     */
    public void setFacId(String facId) {
        this.facId = facId == null ? null : facId.trim();
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
}