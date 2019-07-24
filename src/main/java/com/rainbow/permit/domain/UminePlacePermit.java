package com.rainbow.permit.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "permit_umine_place")
public class UminePlacePermit {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 铀矿冶单位信息,外键

关联表:unit_umine
     */
    @Column(name = "umine_id")
    private String umineId;

    /**
     * 铀尾矿(渣)库名称，外键
关联表：unit_umine_place
     */
    @Column(name = "umine_place_id")
    private String uminePlaceId;

    /**
     * 铀尾矿(渣)库许可阶段外键.
关联表：config_umine_place_permit_stage
     */
    @Column(name = "stage_id")
    private String stageId;

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
     * 许可内容
     */
    @Column(name = "permit_content")
    private String permitContent;

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
    @Column(name = "promise")
    private String reviewPromise;

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
     * 获取铀矿冶单位信息,外键

关联表:unit_umine
     *
     * @return umine_id - 铀矿冶单位信息,外键

关联表:unit_umine
     */
    public String getUmineId() {
        return umineId;
    }

    /**
     * 设置铀矿冶单位信息,外键

关联表:unit_umine
     *
     * @param umineId 铀矿冶单位信息,外键

关联表:unit_umine
     */
    public void setUmineId(String umineId) {
        this.umineId = umineId == null ? null : umineId.trim();
    }

    /**
     * 获取铀尾矿(渣)库名称，外键
关联表：unit_umine_place
     *
     * @return umine_place_id - 铀尾矿(渣)库名称，外键
关联表：unit_umine_place
     */
    public String getUminePlaceId() {
        return uminePlaceId;
    }

    /**
     * 设置铀尾矿(渣)库名称，外键
关联表：unit_umine_place
     *
     * @param uminePlaceId 铀尾矿(渣)库名称，外键
关联表：unit_umine_place
     */
    public void setUminePlaceId(String uminePlaceId) {
        this.uminePlaceId = uminePlaceId == null ? null : uminePlaceId.trim();
    }

    /**
     * 获取铀尾矿(渣)库许可阶段外键.
关联表：config_umine_place_permit_stage
     *
     * @return permit_stage_id - 铀尾矿(渣)库许可阶段外键.
关联表：config_umine_place_permit_stage
     */
    public String getStageId() {
        return stageId;
    }

    /**
     * 设置铀尾矿(渣)库许可阶段外键.
关联表：config_umine_place_permit_stage
     *
     * @param permitStageId 铀尾矿(渣)库许可阶段外键.
关联表：config_umine_place_permit_stage
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
     * @return permit_number - 许可文号
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
     * 获取许可内容
     *
     * @return permit_content - 许可内容
     */
    public String getPermitContent() {
        return permitContent;
    }

    /**
     * 设置许可内容
     *
     * @param permitContent 许可内容
     */
    public void setPermitContent(String permitContent) {
        this.permitContent = permitContent == null ? null : permitContent.trim();
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
     * @return permit_condition - 许可条件
     */
    public String getPermitCondition() {
        return permitCondition;
    }

    /**
     * 设置许可条件
     *
     * @param permitCondition 许可条件
     */
    public void setPermitCondition(String permitCondition) {
        this.permitCondition = permitCondition == null ? null : permitCondition.trim();
    }

    /**
     * 获取审评承诺
     *
     * @return review_promise - 审评承诺
     */
    public String getReviewPromise() {
        return reviewPromise;
    }

    /**
     * 设置审评承诺
     *
     * @param reviewPromise 审评承诺
     */
    public void setReviewPromise(String reviewPromise) {
        this.reviewPromise = reviewPromise == null ? null : reviewPromise.trim();
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