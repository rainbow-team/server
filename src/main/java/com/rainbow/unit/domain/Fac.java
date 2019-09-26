package com.rainbow.unit.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "unit_fac")
public class Fac extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @BeanFieldAnnotation(order = 1)
    public String id;

    /**
     * 核设施名称
     */
    @BeanFieldAnnotation(order = 2)
    public String name;

    /**
     * 核设施编号
     */
    @BeanFieldAnnotation(order = 3)
    public String code;

    /**
     * 核设施营运单位
     * 
     * 关联表:unit_service
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 参建单位
     */
    @BeanFieldAnnotation(order = 5)
    public String builder;

    /**
     * 建造年代
     */
    @Column(name = "build_year")
    @BeanFieldAnnotation(order = 6)
    public Date buildYear;

    /**
     * 监管类别外键
     * 
     * 参考表：config_fac_supervison_category
     */
    @Column(name = "supervision_category_id")
    private String supervisionCategoryId;

    /**
     * 设施类型
     * 
     * 参考表：config_fac_type
     */
    @Column(name = "type_id")
    private String typeId;

    /**
     * 设施状态
     * 
     * 参考表：config_fac_status
     */
    @Column(name = "status_id")
    private String statusId;

    /**
     * 审评状态 参考表：config_review_status
     */
    @Column(name = "review_status_id")
    private String reviewStatusId;

    /**
     * 核设施的许可情况，参考表：config_fac_permit_situation
     */
    @Column(name = "fac_permit_situation_id")
    private String facPermitSituationId;

    /**
     * 是否满足抗震设防登记 0 不满足，1满足
     */
    @BeanFieldAnnotation(order = 14)
    @Column(name = "is_earthquake")
    public Integer isEarthquake;

    /**
     * 是否满足防洪要求0不满足，1满足
     */
    @BeanFieldAnnotation(order = 15)
    @Column(name = "is_flood")
    public Integer isFlood;

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
    @BeanFieldAnnotation(order = 12)
    public String survey;

    /**
     * 场址特征
     */
    @BeanFieldAnnotation(order = 13)
    private String feature;

    /**
     * 工艺描述
     */
    @BeanFieldAnnotation(order = 16)
    @Column(name = "tech_des")
    public String techDes;

    /**
     * 设计基准事故
     */
    @Column(name = "design_standard_accident")
    @BeanFieldAnnotation(order = 17)
    public String designStandardAccident;

    /**
     * 工作人员剂量约束
     */
    @BeanFieldAnnotation(order = 18)
    public String measure;

    /**
     * 备注
     */
    @BeanFieldAnnotation(order = 19)
    public String note;

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
     * 获取核设施名称
     *
     * @return name - 核设施名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置核设施名称
     *
     * @param name 核设施名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取核设施编号
     *
     * @return code - 核设施编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置核设施编号
     *
     * @param code 核设施编号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取核设施营运单位
     * 
     * 关联表:unit_service
     *
     * @return service_id - 核设施营运单位
     * 
     *         关联表:unit_service
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位
     * 
     * 关联表:unit_service
     *
     * @param serviceId 核设施营运单位
     * 
     *                  关联表:unit_service
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取参建单位
     *
     * @return builder - 参建单位
     */
    public String getBuilder() {
        return builder;
    }

    /**
     * 设置参建单位
     *
     * @param builder 参建单位
     */
    public void setBuilder(String builder) {
        this.builder = builder == null ? null : builder.trim();
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
     * 获取监管类别外键
     * 
     * 参考表：config_fac_supervison_category
     *
     * @return supervision_category_id - 监管类别外键
     * 
     *         参考表：config_fac_supervison_category
     */
    public String getSupervisionCategoryId() {
        return supervisionCategoryId;
    }

    /**
     * 设置监管类别外键
     * 
     * 参考表：config_fac_supervison_category
     *
     * @param supervisionCategoryId 监管类别外键
     * 
     *                              参考表：config_fac_supervison_category
     */
    public void setSupervisionCategoryId(String supervisionCategoryId) {
        this.supervisionCategoryId = supervisionCategoryId == null ? null : supervisionCategoryId.trim();
    }

    /**
     * 获取设施类型
     * 
     * 参考表：config_fac_type
     *
     * @return type_id - 设施类型
     * 
     *         参考表：config_fac_type
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置设施类型
     * 
     * 参考表：config_fac_type
     *
     * @param typeId 设施类型
     * 
     *               参考表：config_fac_type
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    /**
     * 获取设施状态
     * 
     * 参考表：config_fac_status
     *
     * @return status_id - 设施状态
     * 
     *         参考表：config_fac_status
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * 设置设施状态
     * 
     * 参考表：config_fac_status
     *
     * @param statusId 设施状态
     * 
     *                 参考表：config_fac_status
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    /**
     * 获取审评状态 参考表：config_review_status
     *
     * @return review_status_id - 审评状态 参考表：config_review_status
     */
    public String getReviewStatusId() {
        return reviewStatusId;
    }

    /**
     * 设置审评状态 参考表：config_review_status
     *
     * @param reviewStatusId 审评状态 参考表：config_review_status
     */
    public void setReviewStatusId(String reviewStatusId) {
        this.reviewStatusId = reviewStatusId == null ? null : reviewStatusId.trim();
    }

    /**
     * 获取核设施的许可情况，参考表：config_fac_permit_situation
     *
     * @return fac_permit_situation_id - 核设施的许可情况，参考表：config_fac_permit_situation
     */
    public String getFacPermitSituationId() {
        return facPermitSituationId;
    }

    /**
     * 设置核设施的许可情况，参考表：config_fac_permit_situation
     *
     * @param facPermitSituationId 核设施的许可情况，参考表：config_fac_permit_situation
     */
    public void setFacPermitSituationId(String facPermitSituationId) {
        this.facPermitSituationId = facPermitSituationId == null ? null : facPermitSituationId.trim();
    }

    /**
     * 获取是否满足抗震设防登记 0 不满足，1满足
     *
     * @return is_earthquake - 是否满足抗震设防登记 0 不满足，1满足
     */
    public Integer getIsEarthquake() {
        return isEarthquake;
    }

    /**
     * 设置是否满足抗震设防登记 0 不满足，1满足
     *
     * @param isEarthquake 是否满足抗震设防登记 0 不满足，1满足
     */
    public void setIsEarthquake(Integer isEarthquake) {
        this.isEarthquake = isEarthquake;
    }

    /**
     * 获取是否满足防洪要求0不满足，1满足
     *
     * @return is_flood - 是否满足防洪要求0不满足，1满足
     */
    public Integer getIsFlood() {
        return isFlood;
    }

    /**
     * 设置是否满足防洪要求0不满足，1满足
     *
     * @param isFlood 是否满足防洪要求0不满足，1满足
     */
    public void setIsFlood(Integer isFlood) {
        this.isFlood = isFlood;
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
     * 获取工艺描述
     *
     * @return tech_des - 工艺描述
     */
    public String getTechDes() {
        return techDes;
    }

    /**
     * 设置工艺描述
     *
     * @param techDes 工艺描述
     */
    public void setTechDes(String techDes) {
        this.techDes = techDes == null ? null : techDes.trim();
    }

    /**
     * 获取设计基准事故
     *
     * @return design_standard_accident - 设计基准事故
     */
    public String getDesignStandardAccident() {
        return designStandardAccident;
    }

    /**
     * 设置设计基准事故
     *
     * @param designStandardAccident 设计基准事故
     */
    public void setDesignStandardAccident(String designStandardAccident) {
        this.designStandardAccident = designStandardAccident == null ? null : designStandardAccident.trim();
    }

    /**
     * 获取工作人员剂量约束
     *
     * @return measure - 工作人员剂量约束
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * 设置工作人员剂量约束
     *
     * @param measure 工作人员剂量约束
     */
    public void setMeasure(String measure) {
        this.measure = measure == null ? null : measure.trim();
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