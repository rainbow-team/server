package com.rainbow.supervision.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "supervisor_train_records")
public class SupervisorTrainRecord {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核安全监督员外键ID，参考表：supervision_suprvisor
     */
    @Column(name = "supervisor_id")
    private String supervisorId;

    /**
     * 培训班次，
外键关联supervision_monitor_train
     */
    @Column(name = "class_id")
    private String classId;

    /**
     * 培训成绩
     */
    private String score;

    /**
     * 监督员证号
     */
    private String number;

    /**
     * 发证日期
     */
    @Column(name = "issue_date")
    private Date issueDate;

    /**
     * 到期时间

按照发证时间自动填入，发证有效期三年
     */
    @Column(name = "expire_date")
    private Date expireDate;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取核安全监督员外键ID，参考表：supervision_suprvisor
     *
     * @return supervisor_id - 核安全监督员外键ID，参考表：supervision_suprvisor
     */
    public String getSupervisorId() {
        return supervisorId;
    }

    /**
     * 设置核安全监督员外键ID，参考表：supervision_suprvisor
     *
     * @param supervisorId 核安全监督员外键ID，参考表：supervision_suprvisor
     */
    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId == null ? null : supervisorId.trim();
    }

    /**
     * 获取培训班次，
外键关联supervision_monitor_train
     *
     * @return class_id - 培训班次，
外键关联supervision_monitor_train
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 设置培训班次，
外键关联supervision_monitor_train
     *
     * @param classId 培训班次，
外键关联supervision_monitor_train
     */
    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    /**
     * 获取培训成绩
     *
     * @return score - 培训成绩
     */
    public String getScore() {
        return score;
    }

    /**
     * 设置培训成绩
     *
     * @param score 培训成绩
     */
    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    /**
     * 获取监督员证号
     *
     * @return number - 监督员证号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置监督员证号
     *
     * @param number 监督员证号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 获取发证日期
     *
     * @return issue_date - 发证日期
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * 设置发证日期
     *
     * @param issueDate 发证日期
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * 获取到期时间

按照发证时间自动填入，发证有效期三年
     *
     * @return expire_date - 到期时间

按照发证时间自动填入，发证有效期三年
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * 设置到期时间

按照发证时间自动填入，发证有效期三年
     *
     * @param expireDate 到期时间

按照发证时间自动填入，发证有效期三年
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}