package com.rainbow.supervision.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "supervision_welder")
public class Welder extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 性别1表示男，0表示女
     */
    private Integer sex;

    /**
     * 聘用单位
     */
    @Column(name = "employ_depart")
    private String employDepart;

    /**
     * 发证单位
     */
    @Column(name = "cert_depart")
    private String certDepart;

    /**
     * 证书编号
     */
    @Column(name = "cert_number")
    private String certNumber;

    /**
     * 焊工编号
     */
    @Column(name = "steel_number")
    private String steelNumber;

    /**
     *焊接人员考试成绩
     */
    @Column(name = "exam_score")
    private String examScore;

    /**
     * 焊接人员考试地点外键，参考表：rainbow.config_welder_exam_place
     */
    @Column(name = "exam_place_id")
    private String examPlaceId;

    /**
     * 有效期限
     */
    @Column(name = "expire_date")
    private Date expireDate;

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
     * 考试合格项目代号
     */
    @Column(name = "exam_project")
    private String examProject;

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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取身份证号
     *
     * @return identity - 身份证号
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置身份证号
     *
     * @param identity 身份证号
     */
    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    /**
     * 获取性别1表示男，0表示女
     *
     * @return sex - 性别1表示男，0表示女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别1表示男，0表示女
     *
     * @param sex 性别1表示男，0表示女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取聘用单位
     *
     * @return employ_depart - 聘用单位
     */
    public String getEmployDepart() {
        return employDepart;
    }

    /**
     * 设置聘用单位
     *
     * @param employDepart 聘用单位
     */
    public void setEmployDepart(String employDepart) {
        this.employDepart = employDepart == null ? null : employDepart.trim();
    }

    /**
     * 获取发证单位
     *
     * @return cert_depart - 发证单位
     */
    public String getCertDepart() {
        return certDepart;
    }

    /**
     * 设置发证单位
     *
     * @param certDepart 发证单位
     */
    public void setCertDepart(String certDepart) {
        this.certDepart = certDepart == null ? null : certDepart.trim();
    }

    /**
     * 获取证书编号
     *
     * @return cert_number - 证书编号
     */
    public String getCertNumber() {
        return certNumber;
    }

    /**
     * 设置证书编号
     *
     * @param certNumber 证书编号
     */
    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber == null ? null : certNumber.trim();
    }

    /**
     * 获取焊工编号
     *
     * @return steel_number - 焊工编号
     */
    public String getSteelNumber() {
        return steelNumber;
    }

    /**
     * 设置焊工编号
     *
     * @param steelNumber 焊工编号
     */
    public void setSteelNumber(String steelNumber) {
        this.steelNumber = steelNumber == null ? null : steelNumber.trim();
    }

    /**
     * 获取考试成绩
     *
     * @return exam_score - 考试成绩
     */
    public String getExamScore() {
        return examScore;
    }

    /**
     * 设置考试成绩
     *
     * @param examScore 考试成绩
     */
    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }

    /**
     * 获取焊接人员考试地点外键，参考表：rainbow.config_welder_exam_place
     *
     * @return exam_place_id - 焊接人员考试地点外键，参考表：rainbow.config_welder_exam_place
     */
    public String getExamPlaceId() {
        return examPlaceId;
    }

    /**
     * 焊接人员考试地点外键，参考表：config_welder_exam_place
     *
     * @param examPlaceId 焊接人员考试地点外键，参考表：config_welder_exam_place
     */
    public void setExamPlaceId(String examPlaceId) {
        this.examPlaceId = examPlaceId;
    }

    /**
     * 获取有效期开始时间
     *
     * @return valid_start - 有效期开始时间
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * 设置有效期开始时间
     *
     * @param expireDate 有效期开始时间
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
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
     * 获取考试合格项目代号
     *
     * @return exam_project - 考试合格项目代号
     */
    public String getExamProject() {
        return examProject;
    }

    /**
     * 设置考试合格项目代号
     *
     * @param examProject 考试合格项目代号
     */
    public void setExamProject(String examProject) {
        this.examProject = examProject == null ? null : examProject.trim();
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