package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.SupervisionMonitorTrain;

import java.util.List;
import java.util.Map;

public interface SupervisionMonitorTrainMapper extends MyMapper<SupervisionMonitorTrain> {

    List<SupervisionMonitorTrain> getMonitorTrainList(Map<String,Object> map);

}