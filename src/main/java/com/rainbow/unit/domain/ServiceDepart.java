package com.rainbow.unit.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "unit_service_depart")
public class ServiceDepart extends BaseExtendEntity {
    /**
     * 核设施营运单位主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    /**
     * 单位名称
     */
    @BeanFieldAnnotation(order = 1)
    public String name;

    /**
     * 所属集团外键，参考表unit_group
     */
    @Column(name = "group_id")
    public String groupId;

    /**
     * 代号
     */
    @BeanFieldAnnotation(order = 5)
    public String code;

    /**
     * 地址
     */
    @BeanFieldAnnotation(order = 6)
    public String address;

    /**
     * 应急电话
     */
    @Column(name = "emergency_tel")
    @BeanFieldAnnotation(order = 7)
    public String emergencyTel;

    /**
     * 传真
     */
    @BeanFieldAnnotation(order = 8)
    public String fax;

    /**
     * 法人代表
     */
    @BeanFieldAnnotation(order = 9)
    public String owner;

    /**
     * 主管安全领导
     */
    @BeanFieldAnnotation(order = 10)
    public String leader;

    /**
     * 主管安全领导电话
     */
    @Column(name = "leader_tel")
    @BeanFieldAnnotation(order = 11)
    public String leaderTel;

    /**
     * 安全部门领导
     */
    @Column(name = "depart_leader")
    @BeanFieldAnnotation(order = 12)
    public String departLeader;

    /**
     * 安全部门领导电话
     */
    @Column(name = "depart_leader_tel")
    @BeanFieldAnnotation(order = 13)
    public String departLeaderTel;

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
     * 基本概况
     */
    @BeanFieldAnnotation(order = 3)
    public String survey;

    /**
     * 厂址特征
     */
    @BeanFieldAnnotation(order = 4)
    public String feature;

    /**
     * 备注
     */
    @BeanFieldAnnotation(order = 14)
    public String note;

    /**
     * 获取核设施营运单位主键
     *
     * @return id - 核设施营运单位主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置核设施营运单位主键
     *
     * @param id 核设施营运单位主键
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
     * 获取所属集团外键，参考表unit_group
     *
     * @return group_id - 所属集团外键，参考表unit_group
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置所属集团外键，参考表unit_group
     *
     * @param groupId 所属集团外键，参考表unit_group
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 获取代号
     *
     * @return code - 代号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代号
     *
     * @param code 代号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * 获取主管安全领导
     *
     * @return leader - 主管安全领导
     */
    public String getLeader() {
        return leader;
    }

    /**
     * 设置主管安全领导
     *
     * @param leader 主管安全领导
     */
    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    /**
     * 获取主管安全领导电话
     *
     * @return leader_tel - 主管安全领导电话
     */
    public String getLeaderTel() {
        return leaderTel;
    }

    /**
     * 设置主管安全领导电话
     *
     * @param leaderTel 主管安全领导电话
     */
    public void setLeaderTel(String leaderTel) {
        this.leaderTel = leaderTel == null ? null : leaderTel.trim();
    }

    /**
     * 获取安全部门领导
     *
     * @return depart_leader - 安全部门领导
     */
    public String getDepartLeader() {
        return departLeader;
    }

    /**
     * 设置安全部门领导
     *
     * @param departLeader 安全部门领导
     */
    public void setDepartLeader(String departLeader) {
        this.departLeader = departLeader == null ? null : departLeader.trim();
    }

    /**
     * 获取安全部门领导电话
     *
     * @return depart_leader_tel - 安全部门领导电话
     */
    public String getDepartLeaderTel() {
        return departLeaderTel;
    }

    /**
     * 设置安全部门领导电话
     *
     * @param departLeaderTel 安全部门领导电话
     */
    public void setDepartLeaderTel(String departLeaderTel) {
        this.departLeaderTel = departLeaderTel == null ? null : departLeaderTel.trim();
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
     * 获取厂址特征
     *
     * @return feature - 厂址特征
     */
    public String getFeature() {
        return feature;
    }

    /**
     * 设置厂址特征
     *
     * @param feature 厂址特征
     */
    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
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