package com.rainbow.monitor.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "monitor_report")
public class ReportMonitor {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核安全监管机构信息，外键

参考表:supervision_org
     */
    @Column(name = "supervision_org_id")
    private String supervisionOrgId;

    /**
     * 报告名称
     */
    private String name;

    /**
     * 监督报告类型，外键

参考表：config_monitor_report_type
     */
    @Column(name = "report_type")
    private String reportType;

    /**
     * 报告时间
     */
    @Column(name = "report_date")
    private Date reportDate;

    /**
     * 是否导入0 否 1 是
     */
    @Column(name = "is_import")
    private Integer isImport;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改人ID
     */
    @Column(name = "modify_id")
    private String modifyId;

    /**
     * 修改时间
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 备注
     */
    private String note;

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
     * 获取核安全监管机构信息，外键

参考表:supervision_org
     *
     * @return supervision_org_id - 核安全监管机构信息，外键

参考表:supervision_org
     */
    public String getSupervisionOrgId() {
        return supervisionOrgId;
    }

    /**
     * 设置核安全监管机构信息，外键

参考表:supervision_org
     *
     * @param supervisionOrgId 核安全监管机构信息，外键

参考表:supervision_org
     */
    public void setSupervisionOrgId(String supervisionOrgId) {
        this.supervisionOrgId = supervisionOrgId == null ? null : supervisionOrgId.trim();
    }

    /**
     * 获取报告名称
     *
     * @return name - 报告名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置报告名称
     *
     * @param name 报告名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取监督报告类型，外键

参考表：config_monitor_report_type
     *
     * @return report_type - 监督报告类型，外键

参考表：config_monitor_report_type
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * 设置监督报告类型，外键

参考表：config_monitor_report_type
     *
     * @param reportType 监督报告类型，外键

参考表：config_monitor_report_type
     */
    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }

    /**
     * 获取报告时间
     *
     * @return date - 报告时间
     */
    public Date getreportDate() {
        return reportDate;
    }

    /**
     * 设置报告时间
     *
     * @param reportDate 报告时间
     */
    public void setreportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * 获取是否导入0 否 1 是
     *
     * @return is_import - 是否导入0 否 1 是
     */
    public Integer getIsImport() {
        return isImport;
    }

    /**
     * 设置是否导入0 否 1 是
     *
     * @param isImport 是否导入0 否 1 是
     */
    public void setIsImport(Integer isImport) {
        this.isImport = isImport;
    }

    /**
     * 获取创建人ID
     *
     * @return creator_id - 创建人ID
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人ID
     *
     * @param creatorId 创建人ID
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改人ID
     *
     * @return modify_id - 修改人ID
     */
    public String getModifyId() {
        return modifyId;
    }

    /**
     * 设置修改人ID
     *
     * @param modifyId 修改人ID
     */
    public void setModifyId(String modifyId) {
        this.modifyId = modifyId == null ? null : modifyId.trim();
    }

    /**
     * 获取修改时间
     *
     * @return modify_date - 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置修改时间
     *
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}