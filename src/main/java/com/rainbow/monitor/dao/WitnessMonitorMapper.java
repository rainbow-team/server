package com.rainbow.monitor.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.domain.extend.WitnessMonitorExtend;

import java.util.List;
import java.util.Map;

public interface WitnessMonitorMapper extends MyMapper<WitnessMonitor> {

    List<WitnessMonitorExtend> getWitnessMonitorList(Map<String,Object> map);

    WitnessMonitorExtend getWitnessMonitorById(String id);

	int verifyDuplication(Map<String, Object> params);
}