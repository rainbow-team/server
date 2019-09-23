package com.rainbow.monitor.domain;

import java.util.Date;
import javax.persistence.*;

import com.rainbow.common.annotation.BeanFieldAnnotation;

@Table(name = "monitor_check")
public class CheckMonitor {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @BeanFieldAnnotation(order = 1)
    public String id;

    /**
     * 核设施营运单位，外键
     * 
     * 参考表：unit_service
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 铀矿冶单位信息,外键
     * 
     * 参考表：unit_umine
     */
    @Column(name = "umine_id")
    private String umineId;

    /**
     * 核安全设备单位，外键
     * 
     * 参考表：unit_equip_depart
     */
    @Column(name = "equip_depart_id")
    private String equipDepartId;

    /**
     * 检查内容
     */
    @BeanFieldAnnotation(order = 5)
    public String content;
    /**
     * 监督检查类型，外键
     * 
     * 参考表：config_monitor_check_type
     */
    @Column(name = "type_id")
    private String typeId;

    /**
     * 检查监督机构外键，参考表supervison_org
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 检查时间
     */
    @Column(name = "start_date")
    @BeanFieldAnnotation(order = 8)
    public Date startDate;

    /**
     * 检查结束时间
     */
    @Column(name = "end_date")
    @BeanFieldAnnotation(order = 9)
    public Date endDate;

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
    private String modify;

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
     * 参考表：unit_service
     *
     * @return service_id - 核设施营运单位，外键
     * 
     *         参考表：unit_service
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位，外键
     * 
     * 参考表：unit_service
     *
     * @param serviceId 核设施营运单位，外键
     * 
     *                  参考表：unit_service
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取铀矿冶单位信息,外键
     * 
     * 参考表：unit_umine
     *
     * @return umine_id - 铀矿冶单位信息,外键
     * 
     *         参考表：unit_umine
     */
    public String getUmineId() {
        return umineId;
    }

    /**
     * 设置铀矿冶单位信息,外键
     * 
     * 参考表：unit_umine
     *
     * @param umineId 铀矿冶单位信息,外键
     * 
     *                参考表：unit_umine
     */
    public void setUmineId(String umineId) {
        this.umineId = umineId == null ? null : umineId.trim();
    }

    /**
     * 获取核安全设备单位，外键
     * 
     * 参考表：unit_equip_depart
     *
     * @return equip_depart_id - 核安全设备单位，外键
     * 
     *         参考表：unit_equip_depart
     */
    public String getEquipDepartId() {
        return equipDepartId;
    }

    /**
     * 设置核安全设备单位，外键
     * 
     * 参考表：unit_equip_depart
     *
     * @param equipDepartId 核安全设备单位，外键
     * 
     *                      参考表：unit_equip_depart
     */
    public void setEquipDepartId(String equipDepartId) {
        this.equipDepartId = equipDepartId == null ? null : equipDepartId.trim();
    }

    /**
     * 获取监督检查类型，外键
     * 
     * 参考表：config_monitor_check_type
     *
     * @return type_id - 监督检查类型，外键
     * 
     *         参考表：config_monitor_check_type
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置监督检查类型，外键
     * 
     * 参考表：config_monitor_check_type
     *
     * @param typeId 监督检查类型，外键
     * 
     *               参考表：config_monitor_check_type
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    /**
     * 获取检查监督机构外键，参考表supervison_org
     *
     * @return org_id - 检查监督机构外键，参考表supervison_org
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置检查监督机构外键，参考表supervison_org
     *
     * @param orgId 检查监督机构外键，参考表supervison_org
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 获取检查时间
     *
     * @return start_date - 检查时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置检查时间
     *
     * @param startDate 检查时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取检查结束时间
     *
     * @return end_date - 检查结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置检查结束时间
     *
     * @param endDate 检查结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * @return modify - 修改人ID
     */
    public String getModify() {
        return modify;
    }

    /**
     * 设置修改人ID
     *
     * @param modify 修改人ID
     */
    public void setModify(String modify) {
        this.modify = modify == null ? null : modify.trim();
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
     * 获取检查内容
     *
     * @return content - 检查内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置检查内容
     *
     * @param content 检查内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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