package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.Expert;

import java.util.List;
import java.util.Map;

public interface ExpertMapper extends MyMapper<Expert> {

    List<Expert> getExpertList(Map<String,Object> map);

    Expert getExpertById(String id);
}