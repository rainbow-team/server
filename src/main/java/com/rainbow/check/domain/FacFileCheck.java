package com.rainbow.check.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_fac_file")
public class FacFileCheck extends BaseExtendEntity {
    /**
     * 主键，用来在file_info中查找具体文件
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    /**
     * 核设施审评信息外键id，参考表:check_fac
     */
    @Column(name = "check_fac_id")
    public String checkFacId;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    public String fileName;

    /**
     * 核设施审评文件类型，
参考表:config_fac_check_file_type
     */
    @Column(name = "fac_check_file_type_id")
    public String facCheckFileTypeId;

    /**
     * 文件时间
     */
    @Column(name = "file_date")
    public Date fileDate;

    /**
     * 文件文号
     */
    @Column(name = "file_no")
    public String fileNo;

    /**
     * 是否导入0 否 1 是
     */
    @Column(name = "is_import")
    public Integer isImport;

    public Integer getIsImport() {
        return isImport;
    }

    public void setIsImport(Integer isImport) {
        this.isImport = isImport;
    }

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
     * 获取核设施审评信息外键id，参考表:check_fac
     *
     * @return check_fac_id - 核设施审评信息外键id，参考表:check_fac
     */
    public String getCheckFacId() {
        return checkFacId;
    }

    /**
     * 设置核设施审评信息外键id，参考表:check_fac
     *
     * @param checkFacId 核设施审评信息外键id，参考表:check_fac
     */
    public void setCheckFacId(String checkFacId) {
        this.checkFacId = checkFacId == null ? null : checkFacId.trim();
    }

    /**
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 获取核设施审评文件类型，
参考表:config_fac_check_file_type
     *
     * @return fac_check_file_type_id - 核设施审评文件类型，
参考表:config_fac_check_file_type
     */
    public String getFacCheckFileTypeId() {
        return facCheckFileTypeId;
    }

    /**
     * 设置核设施审评文件类型，
参考表:config_fac_check_file_type
     *
     * @param facCheckFileTypeId 核设施审评文件类型，
参考表:config_fac_check_file_type
     */
    public void setFacCheckFileTypeId(String facCheckFileTypeId) {
        this.facCheckFileTypeId = facCheckFileTypeId == null ? null : facCheckFileTypeId.trim();
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