package com.rainbow.unit.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "unit_fac_report")
public class FacReport {
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
     * 定期报告类别，外键关联config_fac_report_type
     */
    @Column(name = "type_id")
    private Date typeId;

    /**
     * 定期报告时间
     */
    private Date date;

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
     * 获取定期报告类别，外键关联config_fac_report_type
     *
     * @return type_id - 定期报告类别，外键关联config_fac_report_type
     */
    public Date getTypeId() {
        return typeId;
    }

    /**
     * 设置定期报告类别，外键关联config_fac_report_type
     *
     * @param typeId 定期报告类别，外键关联config_fac_report_type
     */
    public void setTypeId(Date typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取定期报告时间
     *
     * @return date - 定期报告时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置定期报告时间
     *
     * @param date 定期报告时间
     */
    public void setDate(Date date) {
        this.date = date;
    }
}