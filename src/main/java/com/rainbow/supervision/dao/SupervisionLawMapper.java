package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.SupervisionLaw;

import java.util.List;
import java.util.Map;

public interface SupervisionLawMapper extends MyMapper<SupervisionLaw> {

    List<SupervisionLaw> getLawList(Map<String,Object> map);

}