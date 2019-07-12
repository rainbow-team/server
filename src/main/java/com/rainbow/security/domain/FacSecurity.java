package com.rainbow.security.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "security_fac")
public class FacSecurity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核设施营运单位，外键

参考表：unit_service
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 军工核设施名称,外键

参考表:unit_fac，如果是空则为综合性安全问题
     */
    @Column(name = "fac_id")
    private String facId;

    /**
     * 核设施状态，外键

config_fac_status
     */
    @Column(name = "fac_status_id")
    private String facStatusId;

    /**
     * 安全问题检查类型，外键

参考表：config_security_check_type
     */
    @Column(name = "check_type_id")
    private String checkTypeId;

    /**
     * 发现时间
     */
    @Column(name = "find_date")
    private Date findDate;

    /**
     * 核设施安全问题类别，外键。
参考表：config_fac_security_question_type
     */
    @Column(name = "question_type_id")
    private String questionTypeId;

    /**
     * 核设施安全问题性质外键，
参考表：config_fac_security_question_nature
     */
    @Column(name = "question_nature_id")
    private String questionNatureId;

    /**
     * 安全问题整改状态外键，参考表：config_security_reform_status
     */
    @Column(name = "reform_status_id")
    private String reformStatusId;

    /**
     * 整改完成时间
     */
    @Column(name = "reform_complete_date")
    private Date reformCompleteDate;

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
     * 问题内容
     */
    private String content;

    /**
     * 监督要求
     */
    private String require;

    /**
     * 整改方案
     */
    @Column(name = "reform_plan")
    private String reformPlan;

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

参考表：unit_service
     *
     * @return service_id - 核设施营运单位，外键

参考表：unit_service
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位，外键

参考表：unit_service
     *
     * @param serviceId 核设施营运单位，外键

参考表：unit_service
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取军工核设施名称,外键

参考表:unit_fac，如果是空则为综合性安全问题
     *
     * @return fac_id - 军工核设施名称,外键

参考表:unit_fac，如果是空则为综合性安全问题
     */
    public String getFacId() {
        return facId;
    }

    /**
     * 设置军工核设施名称,外键

参考表:unit_fac，如果是空则为综合性安全问题
     *
     * @param facId 军工核设施名称,外键

参考表:unit_fac，如果是空则为综合性安全问题
     */
    public void setFacId(String facId) {
        this.facId = facId == null ? null : facId.trim();
    }

    /**
     * 获取核设施状态，外键

config_fac_status
     *
     * @return fac_status_id - 核设施状态，外键

config_fac_status
     */
    public String getFacStatusId() {
        return facStatusId;
    }

    /**
     * 设置核设施状态，外键

config_fac_status
     *
     * @param facStatusId 核设施状态，外键

config_fac_status
     */
    public void setFacStatusId(String facStatusId) {
        this.facStatusId = facStatusId == null ? null : facStatusId.trim();
    }

    /**
     * 获取安全问题检查类型，外键

参考表：config_security_check_type
     *
     * @return check_type_id - 安全问题检查类型，外键

参考表：config_security_check_type
     */
    public String getCheckTypeId() {
        return checkTypeId;
    }

    /**
     * 设置安全问题检查类型，外键

参考表：config_security_check_type
     *
     * @param checkTypeId 安全问题检查类型，外键

参考表：config_security_check_type
     */
    public void setCheckTypeId(String checkTypeId) {
        this.checkTypeId = checkTypeId == null ? null : checkTypeId.trim();
    }

    /**
     * 获取发现时间
     *
     * @return find_date - 发现时间
     */
    public Date getFindDate() {
        return findDate;
    }

    /**
     * 设置发现时间
     *
     * @param findDate 发现时间
     */
    public void setFindDate(Date findDate) {
        this.findDate = findDate;
    }

    /**
     * 获取核设施安全问题类别，外键。
参考表：config_fac_security_question_type
     *
     * @return question_type_id - 核设施安全问题类别，外键。
参考表：config_fac_security_question_type
     */
    public String getQuestionTypeId() {
        return questionTypeId;
    }

    /**
     * 设置核设施安全问题类别，外键。
参考表：config_fac_security_question_type
     *
     * @param questionTypeId 核设施安全问题类别，外键。
参考表：config_fac_security_question_type
     */
    public void setQuestionTypeId(String questionTypeId) {
        this.questionTypeId = questionTypeId == null ? null : questionTypeId.trim();
    }

    /**
     * 获取核设施安全问题性质外键，
参考表：config_fac_security_question_nature
     *
     * @return question_nature_id - 核设施安全问题性质外键，
参考表：config_fac_security_question_nature
     */
    public String getQuestionNatureId() {
        return questionNatureId;
    }

    /**
     * 设置核设施安全问题性质外键，
参考表：config_fac_security_question_nature
     *
     * @param questionNatureId 核设施安全问题性质外键，
参考表：config_fac_security_question_nature
     */
    public void setQuestionNatureId(String questionNatureId) {
        this.questionNatureId = questionNatureId == null ? null : questionNatureId.trim();
    }

    /**
     * 获取安全问题整改状态外键，参考表：config_security_reform_status
     *
     * @return reform_status_id - 安全问题整改状态外键，参考表：config_security_reform_status
     */
    public String getReformStatusId() {
        return reformStatusId;
    }

    /**
     * 设置安全问题整改状态外键，参考表：config_security_reform_status
     *
     * @param reformStatusId 安全问题整改状态外键，参考表：config_security_reform_status
     */
    public void setReformStatusId(String reformStatusId) {
        this.reformStatusId = reformStatusId == null ? null : reformStatusId.trim();
    }

    /**
     * 获取整改完成时间
     *
     * @return reform_complete_date - 整改完成时间
     */
    public Date getReformCompleteDate() {
        return reformCompleteDate;
    }

    /**
     * 设置整改完成时间
     *
     * @param reformCompleteDate 整改完成时间
     */
    public void setReformCompleteDate(Date reformCompleteDate) {
        this.reformCompleteDate = reformCompleteDate;
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
     * 获取问题内容
     *
     * @return content - 问题内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置问题内容
     *
     * @param content 问题内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取监督要求
     *
     * @return require - 监督要求
     */
    public String getRequire() {
        return require;
    }

    /**
     * 设置监督要求
     *
     * @param require 监督要求
     */
    public void setRequire(String require) {
        this.require = require == null ? null : require.trim();
    }

    /**
     * 获取整改方案
     *
     * @return reform_plan - 整改方案
     */
    public String getReformPlan() {
        return reformPlan;
    }

    /**
     * 设置整改方案
     *
     * @param reformPlan 整改方案
     */
    public void setReformPlan(String reformPlan) {
        this.reformPlan = reformPlan == null ? null : reformPlan.trim();
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