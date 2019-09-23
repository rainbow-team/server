package com.rainbow.monitor.service;

import javax.servlet.http.HttpServletRequest;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.monitor.domain.ReportMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface ReportMonitorService extends IService<ReportMonitor> {

    int addReportMonitor(ReportMonitor reportMonitor);

    int modifyReportMonitor(ReportMonitor reportMonitor);

    ResponseBo getReportMonitorList(Page page);

    ResponseBo getReportMonitorById(String id);

	ResponseBo importData(HttpServletRequest request);
}
