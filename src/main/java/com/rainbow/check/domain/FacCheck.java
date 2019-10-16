package com.rainbow.check.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_fac")
public class FacCheck {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    /**
     * 核设施营运单位，外键

参考表:unit_service
     */
    @Column(name = "service_id")
    public String serviceId;

    /**
     * 核设施名称,外键
参考表：unit_umine_fac

     */
    @Column(name = "fac_id")
    public String facId;

    /**
     * 核设施审评类型，外键

参考表：config_fac_check_type
     */
    @Column(name = "type_id")
    public String typeId;

    /**
     * 审评阶段的外键id，
参考表:config_fac_check_stage
     */
    @Column(name = "stage_id")
    public String stageId;

    /**
     * 审评时间
     */
    @Column(name = "check_date")
    public Date checkDate;

    /**
     * 备注
     */
    public String note;
    /**
     * 是否导入0 否 1 是
     */
    @Column(name = "is_import")
    public Integer isImport;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    public String creatorId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    public Date createDate;

    /**
     * 修改人ID
     */
    @Column(name = "modify_id")
    public String modifyId;

    /**
     * 修改时间
     */
    @Column(name = "modify_date")
    public Date modifyDate;

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

参考表:unit_service
     *
     * @return servive_id - 核设施营运单位，外键

参考表:unit_service
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位，外键

参考表:unit_service
     *
     * @param serviceId 核设施营运单位，外键

参考表:unit_service
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取核设施名称,外键
参考表：unit_umine_fac

     *
     * @return fac_id - 核设施名称,外键
参考表：unit_umine_fac

     */
    public String getFacId() {
        return facId;
    }

    /**
     * 设置核设施名称,外键
参考表：unit_umine_fac

     *
     * @param facId 核设施名称,外键
参考表：unit_umine_fac

     */
    public void setFacId(String facId) {
        this.facId = facId == null ? null : facId.trim();
    }

    /**
     * 获取核设施审评类型，外键

参考表：config_fac_check_type
     *
     * @return type_id - 核设施审评类型，外键

参考表：config_fac_check_type
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置核设施审评类型，外键

参考表：config_fac_check_type
     *
     * @param typeId 核设施审评类型，外键

参考表：config_fac_check_type
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    /**
     * 获取审评阶段的外键id，
参考表:config_fac_check_stage
     *
     * @return stage_id - 审评阶段的外键id，
参考表:config_fac_check_stage
     */
    public String getStageId() {
        return stageId;
    }

    /**
     * 设置审评阶段的外键id，
参考表:config_fac_check_stage
     *
     * @param stageId 审评阶段的外键id，
参考表:config_fac_check_stage
     */
    public void setStageId(String stageId) {
        this.stageId = stageId == null ? null : stageId.trim();
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