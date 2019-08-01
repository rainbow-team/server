package com.rainbow.supervision.domain;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.*;
import java.util.Date;

@Table(name = "supervision_sastind")
public class SupervisionSastind {


    /**
     * 司局名称
     */
    private String name;

    /**
     * 司领导
     */
    private String leader;

    /**
     * 司领导电话
     */
    @Column(name = "leader_tel")
    private String leaderTel;

    /**
     * 分管核安全司领导
     */
    @Column(name = "security_leader")
    private String securityLeader;

    /**
     * 分管核安全司领导电话
     */
    @Column(name = "security_leader_tel")
    private String securityLeaderTel;

    /**
     * 核安全许可处室领导
     */
    @Column(name = "permit_leader")
    private String permitLeader;

    /**
     * 核安全许可处室领导电话
     */
    @Column(name = "permit_leader_tel")
    private String permitLeaderTel;

    /**
     * 核安全监督处室领导
     */
    @Column(name = "supervise_leader")
    private String superviseLeader;

    /**
     * 核安全监督处室领导电话
     */
    @Column(name = "supervise_leader_tel")
    private String superviseLeaderTel;

    /**
     * 处室设置
     */
    @Column(name = "org_set")
    private String orgSet;

    /**
     * 工作职责
     */
    private String duty;

    /**
     * 备注
     */
    private String note;

    /**
     * 国防科工局表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 创建人ID
     */
    @Column(name = "creater_id")
    private String createrId;

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
     * 获取国防科工局表主键
     *
     * @return id - 国防科工局表主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置国防科工局表主键
     *
     * @param id 国防科工局表主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取司局名称
     *
     * @return name - 司局名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置司局名称
     *
     * @param name 司局名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取司领导
     *
     * @return leader - 司领导
     */
    public String getLeader() {
        return leader;
    }

    /**
     * 设置司领导
     *
     * @param leader 司领导
     */
    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    /**
     * 获取司领导电话
     *
     * @return leader_tel - 司领导电话
     */
    public String getLeaderTel() {
        return leaderTel;
    }

    /**
     * 设置司领导电话
     *
     * @param leaderTel 司领导电话
     */
    public void setLeaderTel(String leaderTel) {
        this.leaderTel = leaderTel == null ? null : leaderTel.trim();
    }

    /**
     * 获取分管核安全司领导
     *
     * @return security_leader - 分管核安全司领导
     */
    public String getSecurityLeader() {
        return securityLeader;
    }

    /**
     * 设置分管核安全司领导
     *
     * @param securityLeader 分管核安全司领导
     */
    public void setSecurityLeader(String securityLeader) {
        this.securityLeader = securityLeader == null ? null : securityLeader.trim();
    }

    /**
     * 获取分管核安全司领导电话
     *
     * @return security_leader_tel - 分管核安全司领导电话
     */
    public String getSecurityLeaderTel() {
        return securityLeaderTel;
    }

    /**
     * 设置分管核安全司领导电话
     *
     * @param securityLeaderTel 分管核安全司领导电话
     */
    public void setSecurityLeaderTel(String securityLeaderTel) {
        this.securityLeaderTel = securityLeaderTel == null ? null : securityLeaderTel.trim();
    }

    /**
     * 获取核安全许可处室领导
     *
     * @return permit_leader - 核安全许可处室领导
     */
    public String getPermitLeader() {
        return permitLeader;
    }

    /**
     * 设置核安全许可处室领导
     *
     * @param permitLeader 核安全许可处室领导
     */
    public void setPermitLeader(String permitLeader) {
        this.permitLeader = permitLeader == null ? null : permitLeader.trim();
    }

    /**
     * 获取核安全许可处室领导电话
     *
     * @return permit_leader_tel - 核安全许可处室领导电话
     */
    public String getPermitLeaderTel() {
        return permitLeaderTel;
    }

    /**
     * 设置核安全许可处室领导电话
     *
     * @param permitLeaderTel 核安全许可处室领导电话
     */
    public void setPermitLeaderTel(String permitLeaderTel) {
        this.permitLeaderTel = permitLeaderTel == null ? null : permitLeaderTel.trim();
    }

    /**
     * 获取核安全监督处室领导
     *
     * @return supervise_leader - 核安全监督处室领导
     */
    public String getSuperviseLeader() {
        return superviseLeader;
    }

    /**
     * 设置核安全监督处室领导
     *
     * @param superviseLeader 核安全监督处室领导
     */
    public void setSuperviseLeader(String superviseLeader) {
        this.superviseLeader = superviseLeader == null ? null : superviseLeader.trim();
    }

    /**
     * 获取核安全监督处室领导电话
     *
     * @return supervise_leader_tel - 核安全监督处室领导电话
     */
    public String getSuperviseLeaderTel() {
        return superviseLeaderTel;
    }

    /**
     * 设置核安全监督处室领导电话
     *
     * @param superviseLeaderTel 核安全监督处室领导电话
     */
    public void setSuperviseLeaderTel(String superviseLeaderTel) {
        this.superviseLeaderTel = superviseLeaderTel == null ? null : superviseLeaderTel.trim();
    }

    /**
     * 获取创建人ID
     *
     * @return creater_id - 创建人ID
     */
    public String getCreaterId() {
        return createrId;
    }

    /**
     * 设置创建人ID
     *
     * @param createrId 创建人ID
     */
    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
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
     * 获取处室设置
     *
     * @return org_set - 处室设置
     */
    public String getOrgSet() {
        return orgSet;
    }

    /**
     * 设置处室设置
     *
     * @param orgSet 处室设置
     */
    public void setOrgSet(String orgSet) {
        this.orgSet = orgSet == null ? null : orgSet.trim();
    }

    /**
     * 获取工作职责
     *
     * @return duty - 工作职责
     */
    public String getDuty() {
        return duty;
    }

    /**
     * 设置工作职责
     *
     * @param duty 工作职责
     */
    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
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