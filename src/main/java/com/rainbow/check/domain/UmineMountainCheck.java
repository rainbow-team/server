package com.rainbow.check.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_umine_mountain")
public class UmineMountainCheck {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 铀矿冶单位信息，外键

参考表unit_umine
     */
    @Column(name = "umine_id")
    private String umineId;

    /**
     * 铀矿山信息，外键

参考表：umine_mountain
     */
    @Column(name = "umine_mountain_id")
    private String umineMountainId;

    /**
     * 审查时间
     */
    @Column(name = "check_date")
    private Date checkDate;

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
     * 审查内容
     */
    private String content;

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

参考表unit_umine
     *
     * @return umine_id - 铀矿冶单位信息，外键

参考表unit_umine
     */
    public String getUmineId() {
        return umineId;
    }

    /**
     * 设置铀矿冶单位信息，外键

参考表unit_umine
     *
     * @param umineId 铀矿冶单位信息，外键

参考表unit_umine
     */
    public void setUmineId(String umineId) {
        this.umineId = umineId == null ? null : umineId.trim();
    }

    /**
     * 获取铀矿山信息，外键

参考表：umine_mountain
     *
     * @return umine_mountain_id - 铀矿山信息，外键

参考表：umine_mountain
     */
    public String getUmineMountainId() {
        return umineMountainId;
    }

    /**
     * 设置铀矿山信息，外键

参考表：umine_mountain
     *
     * @param umineMountainId 铀矿山信息，外键

参考表：umine_mountain
     */
    public void setUmineMountainId(String umineMountainId) {
        this.umineMountainId = umineMountainId == null ? null : umineMountainId.trim();
    }

    /**
     * 获取审查时间
     *
     * @return check_date - 审查时间
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 设置审查时间
     *
     * @param checkDate 审查时间
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
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
     * 获取审查内容
     *
     * @return content - 审查内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置审查内容
     *
     * @param content 审查内容
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