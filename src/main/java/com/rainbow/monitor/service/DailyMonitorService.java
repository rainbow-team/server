package com.rainbow.monitor.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.monitor.domain.DailyMonitor;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface DailyMonitorService extends IService<DailyMonitor> {

    int addDailyMonitor(DailyMonitor dailyMonitor);

    int modifyDailyMonitor(DailyMonitor dailyMonitor);

    ResponseBo getDailyMonitorList(Page page);

    ResponseBo getDailyMonitorById(String id);
}
