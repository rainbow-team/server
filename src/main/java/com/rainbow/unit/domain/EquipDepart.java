package com.rainbow.unit.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "unit_equip_depart")
public class EquipDepart extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 应急电话
     */
    @Column(name = "emergency_tel")
    private String emergencyTel;

    /**
     * 传真
     */
    private String fax;

    /**
     * 法人代表
     */
    private String owner;

    /**
     * 主管质量领导
     */
    private String leader;

    /**
     * 主管质量领导电话
     */
    @Column(name = "leader_tel")
    private String leaderTel;

    /**
     * 质量部门领导
     */
    @Column(name = "depart_quality_leader")
    private String departQualityLeader;

    /**
     * 质量部门领导电话
     */
    @Column(name = "depart_quality_leader_tel")
    private String departQualityLeaderTel;

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
     * 基本概况
     */
    private String survey;

    /**
     * 主要产品
     */
    private String product;

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
     * 获取单位名称
     *
     * @return name - 单位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置单位名称
     *
     * @param name 单位名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取应急电话
     *
     * @return emergency_tel - 应急电话
     */
    public String getEmergencyTel() {
        return emergencyTel;
    }

    /**
     * 设置应急电话
     *
     * @param emergencyTel 应急电话
     */
    public void setEmergencyTel(String emergencyTel) {
        this.emergencyTel = emergencyTel == null ? null : emergencyTel.trim();
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * 获取法人代表
     *
     * @return owner - 法人代表
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置法人代表
     *
     * @param owner 法人代表
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * 获取主管质量领导
     *
     * @return leader - 主管质量领导
     */
    public String getLeader() {
        return leader;
    }

    /**
     * 设置主管质量领导
     *
     * @param leader 主管质量领导
     */
    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    /**
     * 获取主管质量领导电话
     *
     * @return leader_tel - 主管质量领导电话
     */
    public String getLeaderTel() {
        return leaderTel;
    }

    /**
     * 设置主管质量领导电话
     *
     * @param leaderTel 主管质量领导电话
     */
    public void setLeaderTel(String leaderTel) {
        this.leaderTel = leaderTel == null ? null : leaderTel.trim();
    }

    /**
     * 获取质量部门领导
     *
     * @return depart_quality_leader - 质量部门领导
     */
    public String getDepartQualityLeader() {
        return departQualityLeader;
    }

    /**
     * 设置质量部门领导
     *
     * @param departQualityLeader 质量部门领导
     */
    public void setDepartQualityLeader(String departQualityLeader) {
        this.departQualityLeader = departQualityLeader == null ? null : departQualityLeader.trim();
    }

    /**
     * 获取质量部门领导电话
     *
     * @return depart_quality_leader_tel - 质量部门领导电话
     */
    public String getDepartQualityLeaderTel() {
        return departQualityLeaderTel;
    }

    /**
     * 设置质量部门领导电话
     *
     * @param departQualityLeaderTel 质量部门领导电话
     */
    public void setDepartQualityLeaderTel(String departQualityLeaderTel) {
        this.departQualityLeaderTel = departQualityLeaderTel == null ? null : departQualityLeaderTel.trim();
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
     * 获取基本概况
     *
     * @return survey - 基本概况
     */
    public String getSurvey() {
        return survey;
    }

    /**
     * 设置基本概况
     *
     * @param survey 基本概况
     */
    public void setSurvey(String survey) {
        this.survey = survey == null ? null : survey.trim();
    }

    /**
     * 获取主要产品
     *
     * @return product - 主要产品
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置主要产品
     *
     * @param product 主要产品
     */
    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
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