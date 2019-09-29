package com.rainbow.monitor.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.monitor.domain.ReportMonitor;
import com.rainbow.monitor.domain.extend.ReportMonitorExtend;

import java.util.List;
import java.util.Map;

public interface ReportMonitorMapper extends MyMapper<ReportMonitor> {

    List<ReportMonitorExtend> getReportMonitorList(Map<String,Object> map);

    ReportMonitor getReportMonitorById(String id);

	int verifyDuplication(Map<String, Object> params);
}