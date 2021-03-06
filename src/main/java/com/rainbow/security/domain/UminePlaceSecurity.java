package com.rainbow.security.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "security_umine_place")
public class UminePlaceSecurity extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 铀矿冶单位信息外键，
     * 
     * 参考表：unit_umine
     */
    @Column(name = "umine_id")
    private String umineId;

    /**
     * 铀尾矿(渣)库信息外键，参考表：unit_umine_place
     */
    @Column(name = "umine_place_id")
    private String uminePlaceId;

    /**
     * 铀尾矿(渣)库设施状态外键
     * 
     * 参考表：config_umine_place_status
     */
    @Column(name = "status_id")
    private String statusId;

    /**
     * 安全问题检查类型，外键
     * 
     * 参考表：config_security_check_type
     */
    @Column(name = "check_type_id")
    private String checkTypeId;

    /**
     * 发现时间
     */
    @Column(name = "find_date")
    @BeanFieldAnnotation(order = 6)
    public Date findDate;

    /**
     * 铀尾矿(渣)库安全问题类别外键。
     * 
     * 参考表：config_umine_place_security_question_type
     */
    @Column(name = "question_type_id")
    private String questionTypeId;

    /**
     * 铀尾矿(渣)库安全问题性质外键，参考表：config_umine_place_security_question_type
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
    @BeanFieldAnnotation(order = 12)
    @Column(name = "reform_complete_date")
    public Date reformCompleteDate;

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
    @BeanFieldAnnotation(order = 5)
    public String content;

    /**
     * 监督要求
     */
    @BeanFieldAnnotation(order = 10)
    @Column(name = "supervise_require")
    public String superviseRequire;

    /**
     * 整改方案
     */
    @BeanFieldAnnotation(order = 11)
    @Column(name = "reform_plan")
    public String reformPlan;

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
     * 获取铀矿冶单位信息外键，
     * 
     * 参考表：unit_umine
     *
     * @return umine_id - 铀矿冶单位信息外键，
     * 
     *         参考表：unit_umine
     */
    public String getUmineId() {
        return umineId;
    }

    /**
     * 设置铀矿冶单位信息外键，
     * 
     * 参考表：unit_umine
     *
     * @param umineId 铀矿冶单位信息外键，
     * 
     *                参考表：unit_umine
     */
    public void setUmineId(String umineId) {
        this.umineId = umineId == null ? null : umineId.trim();
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
     * 获取铀尾矿(渣)库设施状态外键
     * 
     * 参考表：config_umine_place_status
     *
     * @return status_id - 铀尾矿(渣)库设施状态外键
     * 
     *         参考表：config_umine_place_status
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * 设置铀尾矿(渣)库设施状态外键
     * 
     * 参考表：config_umine_place_status
     *
     * @param statusId 铀尾矿(渣)库设施状态外键
     * 
     *                 参考表：config_umine_place_status
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    /**
     * 获取安全问题检查类型，外键
     * 
     * 参考表：config_security_check_type
     *
     * @return check_type_id - 安全问题检查类型，外键
     * 
     *         参考表：config_security_check_type
     */
    public String getCheckTypeId() {
        return checkTypeId;
    }

    /**
     * 设置安全问题检查类型，外键
     * 
     * 参考表：config_security_check_type
     *
     * @param checkTypeId 安全问题检查类型，外键
     * 
     *                    参考表：config_security_check_type
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
     * 获取铀尾矿(渣)库安全问题类别外键。
     * 
     * 参考表：config_umine_place_security_question_type
     *
     * @return question_type_id - 铀尾矿(渣)库安全问题类别外键。
     * 
     *         参考表：config_umine_place_security_question_type
     */
    public String getQuestionTypeId() {
        return questionTypeId;
    }

    /**
     * 设置铀尾矿(渣)库安全问题类别外键。
     * 
     * 参考表：config_umine_place_security_question_type
     *
     * @param questionTypeId 铀尾矿(渣)库安全问题类别外键。
     * 
     *                       参考表：config_umine_place_security_question_type
     */
    public void setQuestionTypeId(String questionTypeId) {
        this.questionTypeId = questionTypeId == null ? null : questionTypeId.trim();
    }

    /**
     * 获取铀尾矿(渣)库安全问题性质外键，参考表：config_umine_place_security_question_type
     *
     * @return question_nature_id -
     *         铀尾矿(渣)库安全问题性质外键，参考表：config_umine_place_security_question_type
     */
    public String getQuestionNatureId() {
        return questionNatureId;
    }

    /**
     * 设置铀尾矿(渣)库安全问题性质外键，参考表：config_umine_place_security_question_type
     *
     * @param questionNatureId 铀尾矿(渣)库安全问题性质外键，参考表：config_umine_place_security_question_type
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
     * @return superviseRequire - 监督要求
     */
    public String getSuperviseRequire() {
        return superviseRequire;
    }

    /**
     * 设置监督要求
     *
     * @param superviseRequire 监督要求
     */
    public void setSuperviseRequire(String superviseRequire) {
        this.superviseRequire = superviseRequire == null ? null : superviseRequire.trim();
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