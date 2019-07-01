package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.OperatorLisence;

import java.util.List;
import java.util.Map;

public interface OperatorLisenceMapper extends MyMapper<OperatorLisence> {

    List<OperatorLisence> getOperatorLisenceList(Map<String,Object> map);
}