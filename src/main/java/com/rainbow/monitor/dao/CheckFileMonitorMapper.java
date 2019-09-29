package com.rainbow.monitor.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.monitor.domain.CheckFileMonitor;
import com.rainbow.monitor.domain.extend.CheckFileMonitorExtend;

import java.util.List;
import java.util.Map;

public interface CheckFileMonitorMapper extends MyMapper<CheckFileMonitor> {

    List<CheckFileMonitorExtend> getCheckFileMonitorList(Map<String,Object> map);

    CheckFileMonitor getCheckFileMonitorById(String id);
}