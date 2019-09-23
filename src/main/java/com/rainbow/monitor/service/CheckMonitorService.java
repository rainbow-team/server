package com.rainbow.monitor.service;

import javax.servlet.http.HttpServletRequest;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.monitor.domain.CheckMonitor;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface CheckMonitorService extends IService<CheckMonitor> {

    int addCheckMonitor(CheckMonitor checkMonitor);

    int modifyCheckMonitor(CheckMonitor checkMonitor);

    int deleteCheckMonitorById(String id);

    ResponseBo getCheckMonitorList(Page page);

    ResponseBo getCheckMonitorById(String id);

    ResponseBo importData(HttpServletRequest request);
}
