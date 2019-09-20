package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.Expert;
import com.rainbow.supervision.domain.extend.ExpertExtend;

import java.util.List;
import java.util.Map;

public interface ExpertMapper extends MyMapper<Expert> {

    List<ExpertExtend> getExpertList(Map<String,Object> map);

    ExpertExtend getExpertById(String id);
}