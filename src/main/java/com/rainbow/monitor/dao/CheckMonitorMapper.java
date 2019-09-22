package com.rainbow.monitor.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.monitor.domain.CheckMonitor;

import java.util.List;
import java.util.Map;

public interface CheckMonitorMapper extends MyMapper<CheckMonitor> {

    CheckMonitor getCheckMonitorById(String id);

    List<CheckMonitor> getCheckMonitorList(Map<String, Object> map);

    int deleteCheckMonitorById(String id);

    // 根据监督检查的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getCheckMonitorRelationCount(String id);

    int verifyDuplication(Map<String, Object> params);
}