package com.rainbow.supervision.domain;

import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.common.annotation.BeanFieldAnnotation;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "supervision_supervisor")
public class Supervisor {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    /**
     * 姓名
     */
    @BeanFieldAnnotation(order = 1)
    public String name;

    /**
     * 身份证号
     */
    @BeanFieldAnnotation(order = 2)
    public String identity;

    /**
     * 出生年月
     */
    @BeanFieldAnnotation(order = 3)
    public Date birthday;

    /**
     * 核安全授权监管机构外键

     参考表：supervision_org
     */
    @Column(name = "org_id")
    public String orgId;

    /**
     * 核安全监督员类别

     参考表：config_supervisor_type
     */
    @Column(name = "type_id")
    public String typeId;


    /**
     * 入职时间
     */
    @Column(name = "entry_date")
    @BeanFieldAnnotation(order = 6)
    public Date entryDate;

    /**
     * 职称外键,参考表：config_title
     */
    @Column(name = "title_id")
    public String titleId;

    /**
     * 职务
     */
    @BeanFieldAnnotation(order = 8)
    public String post;

    /**
     * 政治面貌的外键,参考表：config_political
     */
    @Column(name = "political_id")
    public String politicalId;

    /**
     * 性别
0表示男，1表示女
     */
    public Integer sex;

    /**
     * 联系方式
     */
    @BeanFieldAnnotation(order = 11)
    public String contact;

    /**
     * 从事核安全工作时间
     */
    @BeanFieldAnnotation(order = 12)
    @Column(name = "continue_time")
    public String continueTime;

    /**
     * 学历外键，参考表：config_education
     */
    @Column(name = "education_id")
    public String educationId;

    /**
     * 学位外键。参考表：config_degree
     */
    @Column(name = "degree_id")
    public String degreeId;

    /**
     * 毕业院校
     */
    @Column(name = "education_school")
    @BeanFieldAnnotation(order = 15)
    public String educationSchool;

    /**
     * 毕业时间
     */
    @Column(name = "educate_date")
    @BeanFieldAnnotation(order = 16)
    public Date educateDate;

    /**
     * 专业
     */
    @BeanFieldAnnotation(order = 17)
    public String major;

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
     * 备注
     */
    @BeanFieldAnnotation(order = 18)
    public String note;

    /**
     * 过期时间
     */
    @Column(name = "expire_date")
    @BeanFieldAnnotation(order = 19)
    public Date expireDate;

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public List<FileInfo> attachmentList;

    public List<FileInfo> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<FileInfo> attachmentList) {
        this.attachmentList = attachmentList;
    }

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
     * 获取出生年月
     *
     * @return birthday - 出生年月
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生年月
     *
     * @param birthday 出生年月
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取核安全授权监管机构外键

参考表：supervision_org
     *
     * @return org_id - 核安全授权监管机构外键

参考表：supervision_org
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置核安全授权监管机构外键

参考表：supervision_org
     *
     * @param orgId 核安全授权监管机构外键

参考表：supervision_org
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取入职时间
     *
     * @return entry_date - 入职时间
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * 设置入职时间
     *
     * @param entryDate 入职时间
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * 获取职称外键,参考表：config_title
     *
     * @return title_id - 职称外键,参考表：config_title
     */
    public String getTitleId() {
        return titleId;
    }

    /**
     * 设置职称外键,参考表：config_title
     *
     * @param titleId 职称外键,参考表：config_title
     */
    public void setTitleId(String titleId) {
        this.titleId = titleId == null ? null : titleId.trim();
    }

    /**
     * 获取职务
     *
     * @return post - 职务
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置职务
     *
     * @param post 职务
     */
    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    /**
     * 获取政治面貌的外键,参考表：config_political
     *
     * @return political_id - 政治面貌的外键,参考表：config_political
     */
    public String getPoliticalId() {
        return politicalId;
    }

    /**
     * 设置政治面貌的外键,参考表：config_political
     *
     * @param politicalId 政治面貌的外键,参考表：config_political
     */
    public void setPoliticalId(String politicalId) {
        this.politicalId = politicalId == null ? null : politicalId.trim();
    }

    /**
     * 获取性别
0表示男，1表示女
     *
     * @return sex - 性别
0表示男，1表示女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
0表示男，1表示女
     *
     * @param sex 性别
0表示男，1表示女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取联系方式
     *
     * @return contact - 联系方式
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系方式
     *
     * @param contact 联系方式
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * 获取从事核安全工作时间
     *
     * @return continue_time - 从事核安全工作时间
     */
    public String getContinueTime() {
        return continueTime;
    }

    /**
     * 设置从事核安全工作时间
     *
     * @param continueTime 从事核安全工作时间
     */
    public void setContinueTime(String continueTime) {
        this.continueTime = continueTime == null ? null : continueTime.trim();
    }

    /**
     * 获取学历外键，参考表：config_education
     *
     * @return education_id - 学历外键，参考表：config_education
     */
    public String getEducationId() {
        return educationId;
    }

    /**
     * 设置学历外键，参考表：config_education
     *
     * @param educationId 学历外键，参考表：config_education
     */
    public void setEducationId(String educationId) {
        this.educationId = educationId == null ? null : educationId.trim();
    }

    /**
     * 获取学位外键。参考表：config_degree
     *
     * @return degree_id - 学位外键。参考表：config_degree
     */
    public String getDegreeId() {
        return degreeId;
    }

    /**
     * 设置学位外键。参考表：config_degree
     *
     * @param degreeId 学位外键。参考表：config_degree
     */
    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId == null ? null : degreeId.trim();
    }

    /**
     * 获取毕业院校
     *
     * @return education_school - 毕业院校
     */
    public String getEducationSchool() {
        return educationSchool;
    }

    /**
     * 设置毕业院校
     *
     * @param educationSchool 毕业院校
     */
    public void setEducationSchool(String educationSchool) {
        this.educationSchool = educationSchool == null ? null : educationSchool.trim();
    }

    /**
     * 获取毕业时间
     *
     * @return educate_date - 毕业时间
     */
    public Date getEducateDate() {
        return educateDate;
    }

    /**
     * 设置毕业时间
     *
     * @param educateDate 毕业时间
     */
    public void setEducateDate(Date educateDate) {
        this.educateDate = educateDate;
    }

    /**
     * 获取专业
     *
     * @return major - 专业
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置专业
     *
     * @param major 专业
     */
    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
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