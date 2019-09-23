package com.rainbow.monitor.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.monitor.domain.DailyMonitor;
import com.rainbow.monitor.domain.extend.DailyMonitorExtend;

import java.util.List;
import java.util.Map;

public interface DailyMonitorMapper extends MyMapper<DailyMonitor> {

    DailyMonitorExtend getDailyMonitorById(String id);

    List<DailyMonitorExtend> getDailyMonitorList(Map<String, Object> map);

    int verifyDuplication(Map<String, Object> map);
}