package com.rainbow.permit.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "permit_fac")
public class FacPermit extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核设施营运单位，外键,参考表：unit_service_depart
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 核设施名称,外键
     1、跟营运单位相关联动
     2、关联表umine_fac
     */
    @Column(name = "fac_id")
    private String facId;

    /**
     * 核设施许可阶段,外键.
     参考表：config_fac_permit_stage
     */
    @Column(name = "stage_id")
    private String stageId;

    /**
     * 许可时间
     */
    @Column(name = "permit_date")
    private Date permitDate;

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
     * 许可范围
     */
    private String scope;

    /**
     * 许可条件
     */
    @Column(name = "permit_condition")
    private String condition;

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
     * 获取核设施营运单位，外键,参考表：unit_service_depart
     *
     * @return service_id - 核设施营运单位，外键,参考表：unit_service_depart
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位，外键,参考表：unit_service_depart
     *
     * @param serviceId 核设施营运单位，外键,参考表：unit_service_depart
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取核设施名称,外键
     1、跟营运单位相关联动
     2、关联表umine_fac
     *
     * @return fac_id - 核设施名称,外键
    1、跟营运单位相关联动
    2、关联表umine_fac
     */
    public String getFacId() {
        return facId;
    }

    /**
     * 设置核设施名称,外键
     1、跟营运单位相关联动
     2、关联表umine_fac
     *
     * @param facId 核设施名称,外键
    1、跟营运单位相关联动
    2、关联表umine_fac
     */
    public void setFacId(String facId) {
        this.facId = facId == null ? null : facId.trim();
    }

    /**
     * 获取核设施许可阶段,外键.
     参考表：config_fac_permit_stage
     *
     * @return stage_id - 核设施许可阶段,外键.
    参考表：config_fac_permit_stage
     */
    public String getStageId() {
        return stageId;
    }

    /**
     * 设置核设施许可阶段,外键.
     参考表：config_fac_permit_stage
     *
     * @param stageId 核设施许可阶段,外键.
    参考表：config_fac_permit_stage
     */
    public void setStageId(String stageId) {
        this.stageId = stageId == null ? null : stageId.trim();
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
     * @param PermitDate 许可时间
     */
    public void setPermitDate(Date permitDate) {
        this.permitDate = permitDate;
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
     * 获取许可范围
     *
     * @return scope - 许可范围
     */
    public String getScope() {
        return scope;
    }

    /**
     * 设置许可范围
     *
     * @param scope 许可范围
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * 获取许可条件
     *
     * @return condition - 许可条件
     */
    public String getCondition() {
        return condition;
    }

    /**
     * 设置许可条件
     *
     * @param condition 许可条件
     */
    public void setCondition(String condition) {
        this.condition = condition == null ? null : condition.trim();
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