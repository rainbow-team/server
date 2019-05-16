package com.rainbow.supervision.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "supervision_org")
public class SupervisionOrg {
    /**
     * 授权监管机构基本信息主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 监管机构名称
     */
    private String name;

    /**
     * 机构领导
     */
    private String leader;

    /**
     * 司领导电话
     */
    @Column(name = "leader_tel")
    private String leaderTel;

    /**
     * 是否导入0 否 1 是
     */
    @Column(name = "is_import")
    private Integer isImport;

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
     * 工作职责
     */
    private String duty;

    /**
     * 备注
     */
    private String note;

    /**
     * 获取授权监管机构基本信息主键
     *
     * @return id - 授权监管机构基本信息主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置授权监管机构基本信息主键
     *
     * @param id 授权监管机构基本信息主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取监管机构名称
     *
     * @return name - 监管机构名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置监管机构名称
     *
     * @param name 监管机构名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取机构领导
     *
     * @return leader - 机构领导
     */
    public String getLeader() {
        return leader;
    }

    /**
     * 设置机构领导
     *
     * @param leader 机构领导
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