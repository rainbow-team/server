package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.SupervisionTrainRecord;

import java.util.List;
import java.util.Map;

public interface SupervisionTrainRecordMapper extends MyMapper<SupervisionTrainRecord> {

    List<SupervisionTrainRecord> getTrainRecordList(Map<String,Object> map);
}