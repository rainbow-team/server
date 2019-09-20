package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.OperatorLisence;
import com.rainbow.supervision.domain.extend.OperatorLisenceExtend;

import java.util.List;
import java.util.Map;

public interface OperatorLisenceMapper extends MyMapper<OperatorLisence> {

    List<OperatorLisenceExtend> getOperatorLisenceList(Map<String, Object> map);

    OperatorLisenceExtend getOperatorLisenceById(String id);
}