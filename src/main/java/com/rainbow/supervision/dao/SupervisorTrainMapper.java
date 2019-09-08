package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.SupervisorTrain;

import java.util.List;
import java.util.Map;

public interface SupervisorTrainMapper extends MyMapper<SupervisorTrain> {

    List<SupervisorTrain> getMonitorTrainList(Map<String,Object> map);

    String getClassIdByName(String name);
}