package com.rainbow.check.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_equip_file")
public class EquipFileCheck {
    /**
     * 主键，用来在file_info中查找具体文件
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核设备审评信息外键id，参考表:check_equip
     */
    @Column(name = "check_equip_id")
    private String checkEquipId;

    /**
     * 核设备审评文件类型，
参考表:config_fac_check_file_type
     */
    @Column(name = "equip_check_file_type_id")
    private String equipCheckFileTypeId;

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
     * 获取核设备审评信息外键id，参考表:check_equip
     *
     * @return check_equip_id - 核设备审评信息外键id，参考表:check_equip
     */
    public String getCheckEquipId() {
        return checkEquipId;
    }

    /**
     * 设置核设备审评信息外键id，参考表:check_equip
     *
     * @param checkEquipId 核设备审评信息外键id，参考表:check_equip
     */
    public void setCheckEquipId(String checkEquipId) {
        this.checkEquipId = checkEquipId == null ? null : checkEquipId.trim();
    }


    /**
     * 获取核设备审评文件类型，
参考表:config_fac_check_file_type
     *
     * @return equip_check_file_type_id - 核设备审评文件类型，
参考表:config_fac_check_file_type
     */
    public String getEquipCheckFileTypeId() {
        return equipCheckFileTypeId;
    }

    /**
     * 设置核设备审评文件类型，
参考表:config_fac_check_file_type
     *
     * @param equipCheckFileTypeId 核设备审评文件类型，
参考表:config_fac_check_file_type
     */
    public void setEquipCheckFileTypeId(String equipCheckFileTypeId) {
        this.equipCheckFileTypeId = equipCheckFileTypeId == null ? null : equipCheckFileTypeId.trim();
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