package com.rainbow.supervision.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "supervision_law")
public class SupervisionLaw {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 法规文号
     */
    private String code;

    /**
     * 法规名称
     */
    private String name;

    /**
     * 发布时间
     */
    @Column(name = "release_date")
    private Date releaseDate;

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
     * 获取法规文号
     *
     * @return code - 法规文号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置法规文号
     *
     * @param code 法规文号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取法规名称
     *
     * @return name - 法规名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置法规名称
     *
     * @param name 法规名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取发布时间
     *
     * @return release_date - 发布时间
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * 设置发布时间
     *
     * @param releaseDate 发布时间
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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