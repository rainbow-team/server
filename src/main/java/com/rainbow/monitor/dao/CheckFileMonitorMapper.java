package com.rainbow.monitor.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.monitor.domain.CheckFileMonitor;

import java.util.List;
import java.util.Map;

public interface CheckFileMonitorMapper extends MyMapper<CheckFileMonitor> {

    List<CheckFileMonitor> getCheckFileMonitorList(Map<String,Object> map);

    CheckFileMonitor getCheckFileMonitorById(String id);
}