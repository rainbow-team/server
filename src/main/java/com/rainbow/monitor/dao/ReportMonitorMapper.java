package com.rainbow.monitor.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.monitor.domain.ReportMonitor;

import java.util.List;
import java.util.Map;

public interface ReportMonitorMapper extends MyMapper<ReportMonitor> {

    List<ReportMonitor> getReportMonitorList(Map<String,Object> map);

    ReportMonitor getReportMonitorById(String id);

	int verifyDuplication(Map<String, Object> params);
}