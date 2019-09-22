package com.rainbow.monitor.domain;

import java.util.Date;
import javax.persistence.*;

import com.rainbow.common.annotation.BeanFieldAnnotation;

@Table(name = "monitor_check_file")
public class CheckFileMonitor {
    /**
     * 主键，用来在file_info中查找具体文件
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 监督检查信息 外键id
     * 
     * 参考表:monitor_check
     */
    @Column(name = "monitor_check_id")
    @BeanFieldAnnotation(order = 1)
    public String monitorCheckId;

    /**
     * 监督检查文件类型外键
     * 
     * 参考表:config_monitor_check_file_type
     */
    @Column(name = "monitor_check_file_type_id")
    private String monitorCheckFileTypeId;

    /**
     * 文件文号
     */
    @Column(name = "file_no")
    @BeanFieldAnnotation(order = 3)
    public String fileNo;

    /**
     * 文件时间
     */
    @Column(name = "file_date")
    @BeanFieldAnnotation(order = 4)
    public Date fileDate;

    /**
     * 是否导入0 否 1 是
     */
    @Column(name = "is_import")
    private Integer isImport;

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
     * 获取监督检查信息 外键id
     * 
     * 参考表:monitor_check
     *
     * @return monitor_check_id - 监督检查信息 外键id
     * 
     *         参考表:monitor_check
     */
    public String getMonitorCheckId() {
        return monitorCheckId;
    }

    /**
     * 设置监督检查信息 外键id
     * 
     * 参考表:monitor_check
     *
     * @param monitorCheckId 监督检查信息 外键id
     * 
     *                       参考表:monitor_check
     */
    public void setMonitorCheckId(String monitorCheckId) {
        this.monitorCheckId = monitorCheckId == null ? null : monitorCheckId.trim();
    }

    /**
     * 获取监督检查文件类型外键
     * 
     * 参考表:config_monitor_check_file_type
     *
     * @return monitor_check_file_type_id - 监督检查文件类型外键
     * 
     *         参考表:config_monitor_check_file_type
     */
    public String getMonitorCheckFileTypeId() {
        return monitorCheckFileTypeId;
    }

    /**
     * 设置监督检查文件类型外键
     * 
     * 参考表:config_monitor_check_file_type
     *
     * @param monitorCheckFileTypeId 监督检查文件类型外键
     * 
     *                               参考表:config_monitor_check_file_type
     */
    public void setMonitorCheckFileTypeId(String monitorCheckFileTypeId) {
        this.monitorCheckFileTypeId = monitorCheckFileTypeId == null ? null : monitorCheckFileTypeId.trim();
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
}