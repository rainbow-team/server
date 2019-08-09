package com.rainbow.check.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_activity_file")
public class ActivityFileCheck {
    /**
     * 主键，用来在file_info中查找具体文件
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核活动及其他审评信息外键id，参考表:check_activity
     */
    @Column(name = "check_activity_id")
    private String checkActivityId;


    /**
     * 核活动及其他审评信息文件类型，参考表:config_activity_file_type
     */
    @Column(name = "activity_check_file_type_id")
    private String activityCheckFileTypeId;

    /**
     * 文件时间
     */
    @Column(name = "file_date")
    private Date fileDate;

    /**
     * 文件文号
     */
    @Column(name = "file_no")
    private String fileNo;

    /**
     * 获取主键，用来在file_info中查找具体文件
     *
     * @return id - 主键，用来在file_info中查找具体文件
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键，用来在file_info中查找具体文件
     *
     * @param id 主键，用来在file_info中查找具体文件
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取核活动及其他审评信息外键id，参考表:check_activity
     *
     * @return check_activity_id - 核活动及其他审评信息外键id，参考表:check_activity
     */
    public String getCheckActivityId() {
        return checkActivityId;
    }

    /**
     * 设置核活动及其他审评信息外键id，参考表:check_activity
     *
     * @param checkActivityId 核活动及其他审评信息外键id，参考表:check_activity
     */
    public void setCheckActivityId(String checkActivityId) {
        this.checkActivityId = checkActivityId == null ? null : checkActivityId.trim();
    }

    /**
     * 获取核活动及其他审评信息文件类型，参考表:config_activity_file_type
     *
     * @return activity_check_file_type_id - 核活动及其他审评信息文件类型，参考表:config_activity_file_type
     */
    public String getActivityCheckFileTypeId() {
        return activityCheckFileTypeId;
    }

    /**
     * 设置核活动及其他审评信息文件类型，参考表:config_activity_file_type
     *
     * @param activityCheckFileTypeId 核活动及其他审评信息文件类型，参考表:config_activity_file_type
     */
    public void setActivityCheckFileTypeId(String activityCheckFileTypeId) {
        this.activityCheckFileTypeId = activityCheckFileTypeId == null ? null : activityCheckFileTypeId.trim();
    }

    /**
     * 获取文件时间
     *
     * @return file_date - 文件时间
     */
    public Date getFileDate() {
        return fileDate;
    }

    /**
     * 设置文件时间
     *
     * @param fileDate 文件时间
     */
    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    /**
     * 获取文件文号
     *
     * @return file_no - 文件文号
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * 设置文件文号
     *
     * @param fileNo 文件文号
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo == null ? null : fileNo.trim();
    }
}