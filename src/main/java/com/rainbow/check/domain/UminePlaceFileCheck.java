package com.rainbow.check.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_umine_place_file")
public class UminePlaceFileCheck extends BaseExtendEntity {
    /**
     * 主键，用来在file_info中查找具体文件
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 铀尾矿(渣)库评信息外键id，参考表:check_umine_place
     */
    @Column(name = "check_umine_place_id")
    private String checkUminePlaceId;

    /**
     * 设施/设备/铀尾矿(渣)库审评文件类型，
参考表:config_fac_check_file_type
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
     * 获取铀尾矿(渣)库评信息外键id，参考表:check_umine_place
     *
     * @return check_umine_place_id - 铀尾矿(渣)库评信息外键id，参考表:check_umine_place
     */
    public String getCheckUminePlaceId() {
        return checkUminePlaceId;
    }

    /**
     * 设置铀尾矿(渣)库评信息外键id，参考表:check_umine_place
     *
     * @param checkUminePlaceId 铀尾矿(渣)库评信息外键id，参考表:check_umine_place
     */
    public void setCheckUminePlaceId(String checkUminePlaceId) {
        this.checkUminePlaceId = checkUminePlaceId == null ? null : checkUminePlaceId.trim();
    }

    /**
     * 获取设施/设备/铀尾矿(渣)库审评文件类型，
参考表:config_fac_check_file_type
     *
     * @return check_file_type_id - 设施/设备/铀尾矿(渣)库审评文件类型，
参考表:config_fac_check_file_type
     */
    public String getCheckFileTypeId() {
        return checkFileTypeId;
    }

    /**
     * 设置设施/设备/铀尾矿(渣)库审评文件类型，
参考表:config_fac_check_file_type
     *
     * @param checkFileTypeId 设施/设备/铀尾矿(渣)库审评文件类型，
参考表:config_fac_check_file_type
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