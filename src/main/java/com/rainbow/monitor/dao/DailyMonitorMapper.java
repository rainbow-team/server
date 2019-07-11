package com.rainbow.monitor.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.monitor.domain.DailyMonitor;

import java.util.List;
import java.util.Map;

public interface DailyMonitorMapper extends MyMapper<DailyMonitor> {

    DailyMonitor getDailyMonitorById(String id);

    List<DailyMonitor> getDailyMonitorList(Map<String,Object> map);
}