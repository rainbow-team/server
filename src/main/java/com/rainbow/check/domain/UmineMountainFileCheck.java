package com.rainbow.check.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_umine_mountain_file")
public class UmineMountainFileCheck extends BaseExtendEntity {
    /**
     * 主键，用来在file_info中查找具体文件
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 铀矿山井下消防审评信息外键id，参考表:check_umine_mountain
     */
    @Column(name = "check_umine_mountain_id")
    private String checkUmineMountainId;

    /**
     * 铀矿山井下消防审查文件类型，
参考表:config_umine_mountain_check_file_type
     */
    @Column(name = "check_file_type_id")
    private String checkFileTypeId;

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
     * 获取铀矿山井下消防审评信息外键id，参考表:check_umine_mountain
     *
     * @return check_umine_mountain_id - 铀矿山井下消防审评信息外键id，参考表:check_umine_mountain
     */
    public String getCheckUmineMountainId() {
        return checkUmineMountainId;
    }

    /**
     * 设置铀矿山井下消防审评信息外键id，参考表:check_umine_mountain
     *
     * @param checkUmineMountainId 铀矿山井下消防审评信息外键id，参考表:check_umine_mountain
     */
    public void setCheckUmineMountainId(String checkUmineMountainId) {
        this.checkUmineMountainId = checkUmineMountainId == null ? null : checkUmineMountainId.trim();
    }

    /**
     * 获取铀矿山井下消防审查文件类型，
参考表:config_umine_mountain_check_file_type
     *
     * @return check_file_type_id - 铀矿山井下消防审查文件类型，
参考表:config_umine_mountain_check_file_type
     */
    public String getCheckFileTypeId() {
        return checkFileTypeId;
    }

    /**
     * 设置铀矿山井下消防审查文件类型，
参考表:config_umine_mountain_check_file_type
     *
     * @param checkFileTypeId 铀矿山井下消防审查文件类型，
参考表:config_umine_mountain_check_file_type
     */
    public void setCheckFileTypeId(String checkFileTypeId) {
        this.checkFileTypeId = checkFileTypeId == null ? null : checkFileTypeId.trim();
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