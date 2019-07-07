package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.ServiceAnnualReport;
import com.rainbow.unit.domain.ServiceDepart;

import java.util.List;
import java.util.Map;

public interface ServiceAnnualReportMapper extends MyMapper<ServiceAnnualReport> {

    int deleteReportsByServiceId(String serviceId);

    List<ServiceAnnualReport> getServiceAnnualReportList(Map<String,Object> map);

}