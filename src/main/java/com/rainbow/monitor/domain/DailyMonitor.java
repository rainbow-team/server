package com.rainbow.monitor.domain;

import java.util.Date;
import javax.persistence.*;

import com.rainbow.common.annotation.BeanFieldAnnotation;

@Table(name = "monitor_daily")
public class DailyMonitor {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核设施营运单位，外键
     * 
     * 参考表:unit_service
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 军工核设施名称,外键
     * 
     * 参考表:unit_fac
     */
    @Column(name = "fac_id")
    private String facId;

    /**
     * 核设施状态，外键
     * 
     * 参考表：config_fac_status
     */
    @Column(name = "status_id")
    private String statusId;

    /**
     * 授权监管机构外键，参考表：supervision_org
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 日常监督文件类型，外键参考表：config_monitor_daily_file_type
     */
    @Column(name = "file_type_id")
    @BeanFieldAnnotation(order = 6)
    public String fileTypeId;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    @BeanFieldAnnotation(order = 7)
    public String fileName;

    /**
     * 文件时间
     */
    @Column(name = "file_date")
    private Date fileDate;

    /**
     * 备注
     */
    private String note;

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
     * 获取核设施营运单位，外键
     * 
     * 参考表:unit_service
     *
     * @return service_id - 核设施营运单位，外键
     * 
     *         参考表:unit_service
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位，外键
     * 
     * 参考表:unit_service
     *
     * @param serviceId 核设施营运单位，外键
     * 
     *                  参考表:unit_service
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取军工核设施名称,外键
     * 
     * 参考表:unit_fac
     *
     * @return fac_id - 军工核设施名称,外键
     * 
     *         参考表:unit_fac
     */
    public String getFacId() {
        return facId;
    }

    /**
     * 设置军工核设施名称,外键
     * 
     * 参考表:unit_fac
     *
     * @param facId 军工核设施名称,外键
     * 
     *              参考表:unit_fac
     */
    public void setFacId(String facId) {
        this.facId = facId == null ? null : facId.trim();
    }

    /**
     * 获取核设施状态，外键
     * 
     * 参考表：config_fac_status
     *
     * @return status_id - 核设施状态，外键
     * 
     *         参考表：config_fac_status
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * 设置核设施状态，外键
     * 
     * 参考表：config_fac_status
     *
     * @param statusId 核设施状态，外键
     * 
     *                 参考表：config_fac_status
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    /**
     * 获取授权监管机构外键，参考表：supervision_org
     *
     * @return org_id - 授权监管机构外键，参考表：supervision_org
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置授权监管机构外键，参考表：supervision_org
     *
     * @param orgId 授权监管机构外键，参考表：supervision_org
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 获取日常监督文件类型，外键参考表：config_monitor_daily_file_type
     *
     * @return file_type_id - 日常监督文件类型，外键参考表：config_monitor_daily_file_type
     */
    public String getFileTypeId() {
        return fileTypeId;
    }

    /**
     * 设置日常监督文件类型，外键参考表：config_monitor_daily_file_type
     *
     * @param fileTypeId 日常监督文件类型，外键参考表：config_monitor_daily_file_type
     */
    public void setFileTypeId(String fileTypeId) {
        this.fileTypeId = fileTypeId == null ? null : fileTypeId.trim();
    }

    /**
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 获取文件时间
     *
     * @return file_date - 文件时间
     */
    public Date getFileDate() {
        return fileDate;
    }

    /**
     * 设置文件时间
     *
     * @param fileDate 文件时间
     */
    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
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