package com.rainbow.unit.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "unit_umine_place")
public class Umineplace extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @BeanFieldAnnotation(order = 1)
    public String id;

    /**
     * 铀尾矿(渣)库名称
     */
    @BeanFieldAnnotation(order = 2)
    public String name;

    /**
     * 营运单位,来源于【铀尾矿单位信息】,关联表：unit_service
     */
    @Column(name = "umine_id")
    private String umineId;

    /**
     * 建造年代
     */
    @BeanFieldAnnotation(order = 4)
    @Column(name = "build_year")
    public Date buildYear;

    /**
     * 铀尾矿(渣)库等别，参考表：config_umine_place_level
     */
    @Column(name = "level_id")
    private String levelId;

    /**
     * 铀尾矿(库)设施状态的外键， 参考表：config_umine_place_status
     */
    @Column(name = "status_id")
    private String statusId;

    /**
     * 审评状态，参考表：config_review_status
     */
    @Column(name = "review_status_id")
    private String reviewStatusId;

    /**
     * 铀尾矿(渣)库许可情况的值， 参考表:config_umine_place_permit_situation
     */
    @Column(name = "permit_situation_id")
    private String permitSituationId;

    /**
     * 是否设置坝体监测设施
     */
    @BeanFieldAnnotation(order = 16)
    @Column(name = "have_monitor")
    public Integer haveMonitor;

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
     * 设施简介
     */
    @BeanFieldAnnotation(order = 9)
    public String survey;

    /**
     * 场址特征
     */
    @BeanFieldAnnotation(order = 10)
    public String feature;

    /**
     * 设计有效库容
     */
    @BeanFieldAnnotation(order = 11)
    public String capacity;

    /**
     * 设计洪水重现期
     */
    @BeanFieldAnnotation(order = 12)
    @Column(name = "design_flood_reproduce")
    public String designFloodReproduce;

    /**
     * 校核洪水重现期
     */
    @BeanFieldAnnotation(order = 13)
    @Column(name = "check_flood_reproduce")
    public String checkFloodReproduce;

    /**
     * 初期坝型
     */
    @BeanFieldAnnotation(order = 14)
    @Column(name = "early_dam_type")
    public String earlyDamType;

    /**
     * 初期坝高
     */
    @BeanFieldAnnotation(order = 15)
    @Column(name = "early_dam_height")
    public String earlyDamHeight;

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
     * 获取铀尾矿(渣)库名称
     *
     * @return name - 铀尾矿(渣)库名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置铀尾矿(渣)库名称
     *
     * @param name 铀尾矿(渣)库名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取营运单位,来源于【铀尾矿单位信息】,关联表：unit_service
     *
     * @return umine_id - 营运单位,来源于【铀尾矿单位信息】,关联表：unit_service
     */
    public String getUmineId() {
        return umineId;
    }

    /**
     * 设置营运单位,来源于【铀尾矿单位信息】,关联表：unit_service
     *
     * @param umineId 营运单位,来源于【铀尾矿单位信息】,关联表：unit_service
     */
    public void setUmineId(String umineId) {
        this.umineId = umineId == null ? null : umineId.trim();
    }

    /**
     * 获取建造年代
     *
     * @return build_year - 建造年代
     */
    public Date getBuildYear() {
        return buildYear;
    }

    /**
     * 设置建造年代
     *
     * @param buildYear 建造年代
     */
    public void setBuildYear(Date buildYear) {
        this.buildYear = buildYear;
    }

    /**
     * 获取铀尾矿(渣)库等别，参考表：config_umine_place_level
     *
     * @return level_id - 铀尾矿(渣)库等别，参考表：config_umine_place_level
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * 设置铀尾矿(渣)库等别，参考表：config_umine_place_level
     *
     * @param levelId 铀尾矿(渣)库等别，参考表：config_umine_place_level
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId == null ? null : levelId.trim();
    }

    /**
     * 获取铀尾矿(库)设施状态的外键， 参考表：config_umine_place_status
     *
     * @return status_id - 铀尾矿(库)设施状态的外键， 参考表：config_umine_place_status
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * 设置铀尾矿(库)设施状态的外键， 参考表：config_umine_place_status
     *
     * @param statusId 铀尾矿(库)设施状态的外键， 参考表：config_umine_place_status
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    /**
     * 获取审评状态，参考表：config_review_status
     *
     * @return review_status - 审评状态，参考表：config_review_status
     */
    public String getReviewStatusId() {
        return reviewStatusId;
    }

    /**
     * 设置审评状态，参考表：config_review_status
     *
     * @param reviewStatusId 审评状态，参考表：config_review_status
     */
    public void setReviewStatusId(String reviewStatusId) {
        this.reviewStatusId = reviewStatusId == null ? null : reviewStatusId.trim();
    }

    /**
     * 获取铀尾矿(渣)库许可情况的值， 参考表:config_umine_place_permit_situation
     *
     * @return permit_situation_id - 铀尾矿(渣)库许可情况的值，
     *         参考表:config_umine_place_permit_situation
     */
    public String getPermitSituationId() {
        return permitSituationId;
    }

    /**
     * 设置铀尾矿(渣)库许可情况的值， 参考表:config_umine_place_permit_situation
     *
     * @param permitSituationId 铀尾矿(渣)库许可情况的值，
     *                          参考表:config_umine_place_permit_situation
     */
    public void setPermitSituationId(String permitSituationId) {
        this.permitSituationId = permitSituationId == null ? null : permitSituationId.trim();
    }

    /**
     * 获取是否设置坝体监测设施
     *
     * @return have_monitor - 是否设置坝体监测设施
     */
    public Integer getHaveMonitor() {
        return haveMonitor;
    }

    /**
     * 设置是否设置坝体监测设施
     *
     * @param haveMonitor 是否设置坝体监测设施
     */
    public void setHaveMonitor(Integer haveMonitor) {
        this.haveMonitor = haveMonitor;
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
     * 获取设施简介
     *
     * @return survey - 设施简介
     */
    public String getSurvey() {
        return survey;
    }

    /**
     * 设置设施简介
     *
     * @param survey 设施简介
     */
    public void setSurvey(String survey) {
        this.survey = survey == null ? null : survey.trim();
    }

    /**
     * 获取场址特征
     *
     * @return feature - 场址特征
     */
    public String getFeature() {
        return feature;
    }

    /**
     * 设置场址特征
     *
     * @param feature 场址特征
     */
    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    /**
     * 获取设计有效库容
     *
     * @return capacity - 设计有效库容
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * 设置设计有效库容
     *
     * @param capacity 设计有效库容
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity == null ? null : capacity.trim();
    }

    /**
     * 获取设计洪水重现期
     *
     * @return design_flood_reproduce - 设计洪水重现期
     */
    public String getDesignFloodReproduce() {
        return designFloodReproduce;
    }

    /**
     * 设置设计洪水重现期
     *
     * @param designFloodReproduce 设计洪水重现期
     */
    public void setDesignFloodReproduce(String designFloodReproduce) {
        this.designFloodReproduce = designFloodReproduce == null ? null : designFloodReproduce.trim();
    }

    /**
     * 获取校核洪水重现期
     *
     * @return check_flood_reproduce - 校核洪水重现期
     */
    public String getCheckFloodReproduce() {
        return checkFloodReproduce;
    }

    /**
     * 设置校核洪水重现期
     *
     * @param checkFloodReproduce 校核洪水重现期
     */
    public void setCheckFloodReproduce(String checkFloodReproduce) {
        this.checkFloodReproduce = checkFloodReproduce == null ? null : checkFloodReproduce.trim();
    }

    /**
     * 获取初期坝型
     *
     * @return early_dam_type - 初期坝型
     */
    public String getEarlyDamType() {
        return earlyDamType;
    }

    /**
     * 设置初期坝型
     *
     * @param earlyDamType 初期坝型
     */
    public void setEarlyDamType(String earlyDamType) {
        this.earlyDamType = earlyDamType == null ? null : earlyDamType.trim();
    }

    /**
     * 获取初期坝高
     *
     * @return early_dam_height - 初期坝高
     */
    public String getEarlyDamHeight() {
        return earlyDamHeight;
    }

    /**
     * 设置初期坝高
     *
     * @param earlyDamHeight 初期坝高
     */
    public void setEarlyDamHeight(String earlyDamHeight) {
        this.earlyDamHeight = earlyDamHeight == null ? null : earlyDamHeight.trim();
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