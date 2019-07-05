package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.SupervisionProduceTrain;

import java.util.List;
import java.util.Map;


public interface SupervisionProduceTrainMapper extends MyMapper<SupervisionProduceTrain> {

    List<SupervisionProduceTrain> getTrainRecordList(Map<String,Object> map);

    ResponseBo getTrainRecordById(String id);
}