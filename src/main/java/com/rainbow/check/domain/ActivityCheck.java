package com.rainbow.check.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_activity")
public class ActivityCheck {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核设施营运单位，外键
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 单位名称(外键)，来源于核安全设备单位信息

参考表：unit_equip_depart
     */
    @Column(name = "equip_depart_id")
    private String equipDepartId;

    /**
     * 军工核设施名称,外键

参考表:unit_fac
     */
    @Column(name = "fac_id")
    private String facId;

    /**
     * 核活动类型外键

参考表：config_activity_type
     */
    @Column(name = "type_id")
    private String activityTypeId;

    /**
     * 审评时间
     */
    @Column(name = "check_date")
    private Date checkDate;

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
     * 审查内容
     */
    @Column(name = "check_content")
    private String checkContent;

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
     * 获取核设施营运单位，外键
     *
     * @return service_id - 核设施营运单位，外键
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位，外键
     *
     * @param serviceId 核设施营运单位，外键
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取单位名称(外键)，来源于核安全设备单位信息

参考表：unit_equip_depart
     *
     * @return equip_depart_id - 单位名称(外键)，来源于核安全设备单位信息

参考表：unit_equip_depart
     */
    public String getEquipDepartId() {
        return equipDepartId;
    }

    /**
     * 设置单位名称(外键)，来源于核安全设备单位信息

参考表：unit_equip_depart
     *
     * @param equipDepartId 单位名称(外键)，来源于核安全设备单位信息

参考表：unit_equip_depart
     */
    public void setEquipDepartId(String equipDepartId) {
        this.equipDepartId = equipDepartId == null ? null : equipDepartId.trim();
    }

    /**
     * 获取军工核设施名称,外键

参考表:unit_fac
     *
     * @return fac_id - 军工核设施名称,外键

参考表:unit_fac
     */
    public String getFacId() {
        return facId;
    }

    /**
     * 设置军工核设施名称,外键

参考表:unit_fac
     *
     * @param facId 军工核设施名称,外键

参考表:unit_fac
     */
    public void setFacId(String facId) {
        this.facId = facId == null ? null : facId.trim();
    }

    /**
     * 获取核活动类型外键

参考表：config_activity_type
     *
     * @return activity_type_id - 核活动类型外键

参考表：config_activity_type
     */
    public String getActivityTypeId() {
        return activityTypeId;
    }

    /**
     * 设置核活动类型外键

参考表：config_activity_type
     *
     * @param activityTypeId 核活动类型外键

参考表：config_activity_type
     */
    public void setActivityTypeId(String activityTypeId) {
        this.activityTypeId = activityTypeId == null ? null : activityTypeId.trim();
    }

    /**
     * 获取审评时间
     *
     * @return check_date - 审评时间
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 设置审评时间
     *
     * @param checkDate 审评时间
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
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
     * 获取审查内容
     *
     * @return check_entent - 审查内容
     */
    public String getCheckContent() {
        return checkContent;
    }

    /**
     * 设置审查内容
     *
     * @param checkContent 审查内容
     */
    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent == null ? null : checkContent.trim();
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