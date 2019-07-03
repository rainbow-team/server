package com.rainbow.supervision.domain;

import com.rainbow.attachment.domain.FileInfo;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * 核安全监督培训表
 */

@Table(name = "supervision_monitor_train")
public class SupervisionMonitorTrain {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 培训班次
     */
    private String batch;

    /**
     * 培训开始时间
     */
    @Column(name = "begin_date")
    private Date beginDate;

    /**
     * 培训结束日期
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 培训地点
     */
    private String place;

    /**
     * 参培人数
     */
    private Integer number;

    /**
     * 是否导入0 否 1 是
     */
    @Column(name = "is_import")
    private Integer isImport;

    /**
     * 创建者ID
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改者
     */
    @Column(name = "modify_id")
    private String modifyId;

    /**
     * 修改时间
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 培训内容及教师
     */
    private String content;

    /**
     * 备注
     */
    private String note;

    private List<FileInfo> attachmentList;

    @Transient
    private String creatorName;

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

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
     * 获取培训班次
     *
     * @return batch - 培训班次
     */
    public String getBatch() {
        return batch;
    }

    /**
     * 设置培训班次
     *
     * @param batch 培训班次
     */
    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    /**
     * 获取培训开始时间
     *
     * @return begin_date - 培训开始时间
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置培训开始时间
     *
     * @param beginDate 培训开始时间
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取培训结束日期
     *
     * @return end_date - 培训结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置培训结束日期
     *
     * @param endDate 培训结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取培训地点
     *
     * @return place - 培训地点
     */
    public String getPlace() {
        return place;
    }

    /**
     * 设置培训地点
     *
     * @param place 培训地点
     */
    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    /**
     * 获取参培人数
     *
     * @return number - 参培人数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置参培人数
     *
     * @param number 参培人数
     */
    public void setNumber(Integer number) {
        this.number = number;
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
     * 获取创建者ID
     *
     * @return creator_id - 创建者ID
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建者ID
     *
     * @param creatorId 创建者ID
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
     * 获取修改者
     *
     * @return modify_id - 修改者
     */
    public String getModifyId() {
        return modifyId;
    }

    /**
     * 设置修改者
     *
     * @param modifyId 修改者
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
     * 获取培训内容及教师
     *
     * @return content - 培训内容及教师
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置培训内容及教师
     *
     * @param content 培训内容及教师
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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