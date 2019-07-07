package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.ServiceAnnualReport;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface ServiceAnnualReportService extends IService<ServiceAnnualReport> {

    int addServiceAnnualReport(ServiceAnnualReport annualReport);

    ResponseBo getServiceAnnualReportList(Page page);

    int deleteReportByServiceId(String id);
}
