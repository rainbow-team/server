package com.rainbow.monitor.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.monitor.domain.WitnessMonitor;

import java.util.List;
import java.util.Map;

public interface WitnessMonitorMapper extends MyMapper<WitnessMonitor> {

    List<WitnessMonitor> getWitnessMonitorList(Map<String,Object> map);

    WitnessMonitor getWitnessMonitorById(String id);
}