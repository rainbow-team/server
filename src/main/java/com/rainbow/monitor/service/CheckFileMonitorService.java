package com.rainbow.monitor.service;

import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.monitor.domain.CheckFileMonitor;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface CheckFileMonitorService extends IService<CheckFileMonitor> {

    int addCheckFileMonitor(CheckFileMonitor checkFileMonitor);

    ResponseBo getCheckFileMonitorList(Page page);

    ResponseBo getCheckFileMonitorById(String id);
}
