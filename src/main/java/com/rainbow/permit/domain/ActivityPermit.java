package com.rainbow.permit.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "permit_activity")
public class ActivityPermit {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核设施营运单位,外键

关联表:unit_service
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 单位名称(外键)，来源于核设备单位信息
关联表：unit_equip_depart
     */
    @Column(name = "equip_depart_id")
    private String equipDepartId;

    /**
     * 核设施名称,外键

关联表：unit_fac
     */
    @Column(name = "fac_id")
    private String facId;

    /**
     * 许可名称
     */
    private String name;

    /**
     * 许可内容
     */
    private String content;

    /**
     * 核活动类型外键.
关联表：config_activity_type
     */
    @Column(name = "activity_type_id")
    private String activityTypeId;

    /**
     * 许可时间
     */
    @Column(name = "permit_date")
    private Date permitDate;

    /**
     * 有效期限
     */
    @Column(name = "validate_time")
    private String validateTime;

    /**
     * 许可文号
     */
    private String licence;

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
     * 许可条件
     */
    @Column(name = "permit_condition")
    private String permitCondition;

    /**
     * 审评承诺
     */
    private String promise;

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
     * 获取核设施营运单位,外键

关联表:unit_service
     *
     * @return service_id - 核设施营运单位,外键

关联表:unit_service
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位,外键

关联表:unit_service
     *
     * @param serviceId 核设施营运单位,外键

关联表:unit_service
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取单位名称(外键)，来源于核设备单位信息
关联表：unit_equip_depart
     *
     * @return equip_depart_id - 单位名称(外键)，来源于核设备单位信息
关联表：unit_equip_depart
     */
    public String getEquipDepartId() {
        return equipDepartId;
    }

    /**
     * 设置单位名称(外键)，来源于核设备单位信息
关联表：unit_equip_depart
     *
     * @param equipDepartId 单位名称(外键)，来源于核设备单位信息
关联表：unit_equip_depart
     */
    public void setEquipDepartId(String equipDepartId) {
        this.equipDepartId = equipDepartId == null ? null : equipDepartId.trim();
    }

    /**
     * 获取核设施名称,外键

关联表：unit_fac
     *
     * @return fac_id - 核设施名称,外键

关联表：unit_fac
     */
    public String getFacId() {
        return facId;
    }

    /**
     * 设置核设施名称,外键

关联表：unit_fac
     *
     * @param facId 核设施名称,外键

关联表：unit_fac
     */
    public void setFacId(String facId) {
        this.facId = facId == null ? null : facId.trim();
    }

    /**
     * 获取许可名称
     *
     * @return name - 许可名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置许可名称
     *
     * @param name 许可名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取许可内容
     *
     * @return content - 许可内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置许可内容
     *
     * @param content 许可内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取核活动类型外键.
关联表：config_activity_type
     *
     * @return activity_type_id - 核活动类型外键.
关联表：config_activity_type
     */
    public String getActivityTypeId() {
        return activityTypeId;
    }

    /**
     * 设置核活动类型外键.
关联表：config_activity_type
     *
     * @param activityTypeId 核活动类型外键.
关联表：config_activity_type
     */
    public void setActivityTypeId(String activityTypeId) {
        this.activityTypeId = activityTypeId == null ? null : activityTypeId.trim();
    }

    /**
     * 获取许可时间
     *
     * @return permit_date - 许可时间
     */
    public Date getPermitDate() {
        return permitDate;
    }

    /**
     * 设置许可时间
     *
     * @param permitDate 许可时间
     */
    public void setPermitDate(Date permitDate) {
        this.permitDate = permitDate;
    }

    /**
     * 获取有效期限
     *
     * @return validate_time - 有效期限
     */
    public String getValidateTime() {
        return validateTime;
    }

    /**
     * 设置有效期限
     *
     * @param validateTime 有效期限
     */
    public void setValidateTime(String validateTime) {
        this.validateTime = validateTime == null ? null : validateTime.trim();
    }

    /**
     * 获取许可文号
     *
     * @return licence - 许可文号
     */
    public String getLicence() {
        return licence;
    }

    /**
     * 设置许可文号
     *
     * @param licence 许可文号
     */
    public void setLicence(String licence) {
        this.licence = licence == null ? null : licence.trim();
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
     * 获取许可条件
     *
     * @return permitCondition - 许可条件
     */
    public String getpermitCondition() {
        return permitCondition;
    }

    /**
     * 设置许可条件
     *
     * @param permitCondition 许可条件
     */
    public void setpermitCondition(String permitCondition) {
        this.permitCondition = permitCondition == null ? null : permitCondition.trim();
    }

    /**
     * 获取审评承诺
     *
     * @return promise - 审评承诺
     */
    public String getPromise() {
        return promise;
    }

    /**
     * 设置审评承诺
     *
     * @param promise 审评承诺
     */
    public void setPromise(String promise) {
        this.promise = promise == null ? null : promise.trim();
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