package com.rainbow.permit.domain;

import com.rainbow.attachment.domain.FileInfo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "permit_umine_mountain")
public class UmineMountainPermit {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 铀矿冶单位信息，外键

     关联表:unit_umine
     */
    @Column(name = "umine_id")
    private String umineId;

    /**
     * 铀矿山信息，外键

     参考表：unit_umine_mountain
     */
    @Column(name = "umine_mountain_id")
    private String umineMountainId;

    /**
     * 备案时间
     */
    @Column(name = "record_date")
    private Date recordDate;

    /**
     * 备案文号
     */
    @Column(name = "record_number")
    private String recordNumber;

    /**
     * 备案附件的id，用来在file_info中查找
     */
    @Column(name = "record_attach_id")
    private String recordAttachId;

    /**
     * 验收时间
     */
    @Column(name = "accept_date")
    private Date acceptDate;

    /**
     * 验收文号
     */
    @Column(name = "accept_number")
    private String acceptNumber;

    /**
     * 验收文件附件主键ID，用来在file_info中查找
     */
    @Column(name = "accept_attach_id")
    private String acceptAttachId;

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
     * 备案条件
     */
    @Column(name = "record_condition")
    private String recordCondition;

    /**
     * 审评承诺
     */
    @Column(name = "review_promise")
    private String reviewPromise;

    /**
     * 主要验收结论
     */
    @Column(name = "accept_conclusion")
    private String acceptConclusion;

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
     * 获取铀矿冶单位信息，外键

     关联表:unit_umine
     *
     * @return umine_id - 铀矿冶单位信息，外键

    关联表:unit_umine
     */
    public String getUmineId() {
        return umineId;
    }

    /**
     * 设置铀矿冶单位信息，外键

     关联表:unit_umine
     *
     * @param umineId 铀矿冶单位信息，外键

    关联表:unit_umine
     */
    public void setUmineId(String umineId) {
        this.umineId = umineId == null ? null : umineId.trim();
    }

    /**
     * 获取铀矿山信息，外键

     参考表：unit_umine_mountain
     *
     * @return umine_mountain_id - 铀矿山信息，外键

    参考表：unit_umine_mountain
     */
    public String getUmineMountainId() {
        return umineMountainId;
    }

    /**
     * 设置铀矿山信息，外键

     参考表：unit_umine_mountain
     *
     * @param umineMountainId 铀矿山信息，外键

    参考表：unit_umine_mountain
     */
    public void setUmineMountainId(String umineMountainId) {
        this.umineMountainId = umineMountainId == null ? null : umineMountainId.trim();
    }

    /**
     * 获取备案时间
     *
     * @return record_date - 备案时间
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 设置备案时间
     *
     * @param recordDate 备案时间
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * 获取备案文号
     *
     * @return record_number - 备案文号
     */
    public String getRecordNumber() {
        return recordNumber;
    }

    /**
     * 设置备案文号
     *
     * @param recordNumber 备案文号
     */
    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber == null ? null : recordNumber.trim();
    }

    /**
     * 获取备案附件的id，用来在file_info中查找
     *
     * @return record_attach_id - 备案附件的id，用来在file_info中查找
     */
    public String getRecordAttachId() {
        return recordAttachId;
    }

    /**
     * 设置备案附件的id，用来在file_info中查找
     *
     * @param recordAttachId 备案附件的id，用来在file_info中查找
     */
    public void setRecordAttachId(String recordAttachId) {
        this.recordAttachId = recordAttachId == null ? null : recordAttachId.trim();
    }

    /**
     * 获取验收时间
     *
     * @return accept_date - 验收时间
     */
    public Date getAcceptDate() {
        return acceptDate;
    }

    /**
     * 设置验收时间
     *
     * @param acceptDate 验收时间
     */
    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    /**
     * 获取验收文号
     *
     * @return accept_number - 验收文号
     */
    public String getAcceptNumber() {
        return acceptNumber;
    }

    /**
     * 设置验收文号
     *
     * @param acceptNumber 验收文号
     */
    public void setAcceptNumber(String acceptNumber) {
        this.acceptNumber = acceptNumber == null ? null : acceptNumber.trim();
    }

    /**
     * 获取验收文件附件主键ID，用来在file_info中查找
     *
     * @return accept_attach_id - 验收文件附件主键ID，用来在file_info中查找
     */
    public String getAcceptAttachId() {
        return acceptAttachId;
    }

    /**
     * 设置验收文件附件主键ID，用来在file_info中查找
     *
     * @param acceptAttachId 验收文件附件主键ID，用来在file_info中查找
     */
    public void setAcceptAttachId(String acceptAttachId) {
        this.acceptAttachId = acceptAttachId == null ? null : acceptAttachId.trim();
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
     * 获取备案条件
     *
     * @return record_condition - 备案条件
     */
    public String getRecordCondition() {
        return recordCondition;
    }

    /**
     * 设置备案条件
     *
     * @param recordCondition 备案条件
     */
    public void setRecordCondition(String recordCondition) {
        this.recordCondition = recordCondition == null ? null : recordCondition.trim();
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
     * 获取主要验收结论
     *
     * @return accept_conclusion - 主要验收结论
     */
    public String getAcceptConclusion() {
        return acceptConclusion;
    }

    /**
     * 设置主要验收结论
     *
     * @param acceptConclusion 主要验收结论
     */
    public void setAcceptConclusion(String acceptConclusion) {
        this.acceptConclusion = acceptConclusion == null ? null : acceptConclusion.trim();
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

    private List<FileInfo> backupAttachmentList;

    public List<FileInfo> getBackupAttachmentList() {
        return backupAttachmentList;
    }

    public void setBackupAttachmentList(List<FileInfo> backupAttachmentList) {
        this.backupAttachmentList = backupAttachmentList;
    }

    private List<FileInfo> acceptpAttachmentList;

    public List<FileInfo> getAcceptpAttachmentList() {
        return acceptpAttachmentList;
    }

    public void setAcceptpAttachmentList(List<FileInfo> acceptpAttachmentList) {
        this.acceptpAttachmentList = acceptpAttachmentList;
    }
}