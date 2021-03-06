package com.rainbow.unit.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "unit_service_annual_report")
public class ServiceAnnualReport extends BaseExtendEntity {
    /**
     * 核设施营运单位年度报告id,file_info关联查找
     */
    @Id
    @Column(name = "report_id")
    public String reportId;

    /**
     * 报告年度
     */
    @Column(name = "report_year")
    @BeanFieldAnnotation(order = 2)
    public Date reportYear;

    /**
     * 核设施运营单位外键
     */
    @Column(name = "service_id")
    public String serviceId;

    @Transient
    @BeanFieldAnnotation(order = 1)
    public String serviceName;

    /**
     * 获取核设施营运单位年度报告id,file_info关联查找
     *
     * @return report_id - 核设施营运单位年度报告id,file_info关联查找
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * 设置核设施营运单位年度报告id,file_info关联查找
     *
     * @param reportId 核设施营运单位年度报告id,file_info关联查找
     */
    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    /**
     * 获取报告年度
     *
     * @return report_year - 报告年度
     */
    public Date getReportYear() {
        return reportYear;
    }

    /**
     * 设置报告年度
     *
     * @param reportYear 报告年度
     */
    public void setReportYear(Date reportYear) {
        this.reportYear = reportYear;
    }

    /**
     * 获取核设施运营单位外键
     *
     * @return service_id - 核设施运营单位外键
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施运营单位外键
     *
     * @param serviceId 核设施运营单位外键
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
