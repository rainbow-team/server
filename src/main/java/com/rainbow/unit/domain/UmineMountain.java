package com.rainbow.unit.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "unit_umine_mountain")
public class UmineMountain extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 铀矿山名称
     */
    private String name;

    /**
     * 营运单位,来源于【铀矿冶单位表】：unit_umine
     */
    @Column(name = "umine_id")
    private String umineId;

    /**
     * 建造年代
     */
    @Column(name = "build_year")
    private Date buildYear;

    /**
     * 铀矿山设施状态的外键，
关联表：config_umine_mountain_status
     */
    @Column(name = "status_id")
    private String statusId;

    /**
     * 井下消防审查备案情况 0 未备案 ，1已备案
     */
    @Column(name = "is_record")
    private Integer isRecord;

    /**
     * 井下消防验收情况 0未验收 ，1已验收
     */
    @Column(name = "is_accept")
    private Integer isAccept;

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
     * 井下消防设计简介
     */
    private String survey;

    /**
     * 井下重点火灾危险源
     */
    @Column(name = "danger_firepoint")
    private String dangerFirepoint;

    /**
     * 消防水池容积
     */
    @Column(name = "water_volumn")
    private String waterVolumn;

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
     * 获取铀矿山名称
     *
     * @return name - 铀矿山名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置铀矿山名称
     *
     * @param name 铀矿山名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取营运单位,来源于【铀矿冶单位表】：unit_umine
     *
     * @return umine_id - 营运单位,来源于【铀矿冶单位表】：unit_umine
     */
    public String getUmineId() {
        return umineId;
    }

    /**
     * 设置营运单位,来源于【铀矿冶单位表】：unit_umine
     *
     * @param umineId 营运单位,来源于【铀矿冶单位表】：unit_umine
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
     * 获取铀矿山设施状态的外键，
关联表：config_umine_mountain_status
     *
     * @return status_id - 铀矿山设施状态的外键，
关联表：config_umine_mountain_status
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * 设置铀矿山设施状态的外键，
关联表：config_umine_mountain_status
     *
     * @param statusId 铀矿山设施状态的外键，
关联表：config_umine_mountain_status
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    /**
     * 获取井下消防审查备案情况 0 未备案 ，1已备案
     *
     * @return is_record - 井下消防审查备案情况 0 未备案 ，1已备案
     */
    public Integer getIsRecord() {
        return isRecord;
    }

    /**
     * 设置井下消防审查备案情况 0 未备案 ，1已备案
     *
     * @param isRecord 井下消防审查备案情况 0 未备案 ，1已备案
     */
    public void setIsRecord(Integer isRecord) {
        this.isRecord = isRecord;
    }

    /**
     * 获取井下消防验收情况 0未验收 ，1已验收
     *
     * @return is_accept - 井下消防验收情况 0未验收 ，1已验收
     */
    public Integer getIsAccept() {
        return isAccept;
    }

    /**
     * 设置井下消防验收情况 0未验收 ，1已验收
     *
     * @param isAccept 井下消防验收情况 0未验收 ，1已验收
     */
    public void setIsAccept(Integer isAccept) {
        this.isAccept = isAccept;
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
     * 获取井下消防设计简介
     *
     * @return survey - 井下消防设计简介
     */
    public String getSurvey() {
        return survey;
    }

    /**
     * 设置井下消防设计简介
     *
     * @param survey 井下消防设计简介
     */
    public void setSurvey(String survey) {
        this.survey = survey == null ? null : survey.trim();
    }

    /**
     * 获取井下重点火灾危险源
     *
     * @return danger_firepoint - 井下重点火灾危险源
     */
    public String getDangerFirepoint() {
        return dangerFirepoint;
    }

    /**
     * 设置井下重点火灾危险源
     *
     * @param dangerFirepoint 井下重点火灾危险源
     */
    public void setDangerFirepoint(String dangerFirepoint) {
        this.dangerFirepoint = dangerFirepoint == null ? null : dangerFirepoint.trim();
    }

    /**
     * 获取消防水池容积
     *
     * @return water_volumn - 消防水池容积
     */
    public String getWaterVolumn() {
        return waterVolumn;
    }

    /**
     * 设置消防水池容积
     *
     * @param waterVolumn 消防水池容积
     */
    public void setWaterVolumn(String waterVolumn) {
        this.waterVolumn = waterVolumn == null ? null : waterVolumn.trim();
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