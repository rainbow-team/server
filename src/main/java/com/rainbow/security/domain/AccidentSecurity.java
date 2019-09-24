package com.rainbow.security.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "security_accident")
public class AccidentSecurity extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核设施营运单位信息外键，参考表unit_service
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 铀矿冶单位外键，参考表unit_umine
     */
    @Column(name = "umine_id")
    private String umineId;

    /**
     * 核设施信息外键，参考表unit_fac
     */
    @Column(name = "fac_id")
    private String facId;

    /**
     * 铀尾矿(渣)库信息外键，参考表：unit_umine_place
     */
    @Column(name = "umine_place_id")
    private String uminePlaceId;

    /**
     * 核设施状态外键，参考表：config_fac_status
     */
    @Column(name = "fac_status_id")
    private String facStatusId;

    /**
     * 铀尾矿(渣)库状态外键， 参考表：config_umine_place_status
     */
    @Column(name = "umine_place_status_id")
    private String uminePlaceStatusId;

    /**
     * 事件名称
     */
    @BeanFieldAnnotation(order = 7)
    public String name;

    /**
     * 发生时间
     */
    @Column(name = "occur_date")
    @BeanFieldAnnotation(order = 8)
    public Date occurDate;

    /**
     * 事故时间类别外键， 参考表：config_accident_type
     */
    @Column(name = "type_id")
    private String typeId;

    /**
     * 事故时间性质外键， 参考表：config_accident_nature
     */
    @Column(name = "nature_id")
    private String natureId;

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
     * 事件过程
     */
    @BeanFieldAnnotation(order = 9)
    public String process;

    /**
     * 事件后果
     */
    @BeanFieldAnnotation(order = 10)
    public String consequence;

    /**
     * 原因分析
     */
    @BeanFieldAnnotation(order = 11)
    public String reason;

    /**
     * 处理措施
     */
    @BeanFieldAnnotation(order = 14)
    public String measure;

    /**
     * 反馈
     */
    @BeanFieldAnnotation(order = 15)
    public String feedback;

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
     * 获取核设施营运单位信息外键，参考表unit_service
     *
     * @return service_id - 核设施营运单位信息外键，参考表unit_service
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位信息外键，参考表unit_service
     *
     * @param serviceId 核设施营运单位信息外键，参考表unit_service
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取铀矿冶单位外键，参考表unit_umine
     *
     * @return umine_id - 铀矿冶单位外键，参考表unit_umine
     */
    public String getUmineId() {
        return umineId;
    }

    /**
     * 设置铀矿冶单位外键，参考表unit_umine
     *
     * @param umineId 铀矿冶单位外键，参考表unit_umine
     */
    public void setUmineId(String umineId) {
        this.umineId = umineId == null ? null : umineId.trim();
    }

    /**
     * 获取核设施信息外键，参考表unit_fac
     *
     * @return fac_id - 核设施信息外键，参考表unit_fac
     */
    public String getFacId() {
        return facId;
    }

    /**
     * 设置核设施信息外键，参考表unit_fac
     *
     * @param facId 核设施信息外键，参考表unit_fac
     */
    public void setFacId(String facId) {
        this.facId = facId == null ? null : facId.trim();
    }

    /**
     * 获取铀尾矿(渣)库信息外键，参考表：unit_umine_place
     *
     * @return umine_place_id - 铀尾矿(渣)库信息外键，参考表：unit_umine_place
     */
    public String getUminePlaceId() {
        return uminePlaceId;
    }

    /**
     * 设置铀尾矿(渣)库信息外键，参考表：unit_umine_place
     *
     * @param uminePlaceId 铀尾矿(渣)库信息外键，参考表：unit_umine_place
     */
    public void setUminePlaceId(String uminePlaceId) {
        this.uminePlaceId = uminePlaceId == null ? null : uminePlaceId.trim();
    }

    /**
     * 获取核设施状态外键，参考表：config_fac_status
     *
     * @return fac_status_id - 核设施状态外键，参考表：config_fac_status
     */
    public String getFacStatusId() {
        return facStatusId;
    }

    /**
     * 设置核设施状态外键，参考表：config_fac_status
     *
     * @param facStatusId 核设施状态外键，参考表：config_fac_status
     */
    public void setFacStatusId(String facStatusId) {
        this.facStatusId = facStatusId == null ? null : facStatusId.trim();
    }

    /**
     * 获取铀尾矿(渣)库状态外键， 参考表：config_umine_place_status
     *
     * @return umine_place_status_id - 铀尾矿(渣)库状态外键， 参考表：config_umine_place_status
     */
    public String getUminePlaceStatusId() {
        return uminePlaceStatusId;
    }

    /**
     * 设置铀尾矿(渣)库状态外键， 参考表：config_umine_place_status
     *
     * @param uminePlaceStatusId 铀尾矿(渣)库状态外键， 参考表：config_umine_place_status
     */
    public void setUminePlaceStatusId(String uminePlaceStatusId) {
        this.uminePlaceStatusId = uminePlaceStatusId == null ? null : uminePlaceStatusId.trim();
    }

    /**
     * 获取事件名称
     *
     * @return name - 事件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置事件名称
     *
     * @param name 事件名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取发生时间
     *
     * @return occur_date - 发生时间
     */
    public Date getOccurDate() {
        return occurDate;
    }

    /**
     * 设置发生时间
     *
     * @param occurDate 发生时间
     */
    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    /**
     * 获取事故时间类别外键， 参考表：config_accident_type
     *
     * @return type_id - 事故时间类别外键， 参考表：config_accident_type
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置事故时间类别外键， 参考表：config_accident_type
     *
     * @param typeId 事故时间类别外键， 参考表：config_accident_type
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    /**
     * 获取事故时间性质外键， 参考表：config_accident_nature
     *
     * @return nature_id - 事故时间性质外键， 参考表：config_accident_nature
     */
    public String getNatureId() {
        return natureId;
    }

    /**
     * 设置事故时间性质外键， 参考表：config_accident_nature
     *
     * @param natureId 事故时间性质外键， 参考表：config_accident_nature
     */
    public void setNatureId(String natureId) {
        this.natureId = natureId == null ? null : natureId.trim();
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
     * 获取事件过程
     *
     * @return process - 事件过程
     */
    public String getProcess() {
        return process;
    }

    /**
     * 设置事件过程
     *
     * @param process 事件过程
     */
    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    /**
     * 获取事件后果
     *
     * @return consequence - 事件后果
     */
    public String getConsequence() {
        return consequence;
    }

    /**
     * 设置事件后果
     *
     * @param consequence 事件后果
     */
    public void setConsequence(String consequence) {
        this.consequence = consequence == null ? null : consequence.trim();
    }

    /**
     * 获取原因分析
     *
     * @return reason - 原因分析
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置原因分析
     *
     * @param reason 原因分析
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 获取处理措施
     *
     * @return measure - 处理措施
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * 设置处理措施
     *
     * @param measure 处理措施
     */
    public void setMeasure(String measure) {
        this.measure = measure == null ? null : measure.trim();
    }

    /**
     * 获取反馈
     *
     * @return feedback - 反馈
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * 设置反馈
     *
     * @param feedback 反馈
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
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