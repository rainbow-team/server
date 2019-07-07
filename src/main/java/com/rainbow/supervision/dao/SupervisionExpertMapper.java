package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.SupervisionExpert;
import com.rainbow.supervision.domain.SupervisionLaw;

import java.util.List;
import java.util.Map;

public interface SupervisionExpertMapper extends MyMapper<SupervisionExpert> {

    List<SupervisionExpert> getExpertList(Map<String,Object> map);

    SupervisionExpert getExpertById(String id);
}