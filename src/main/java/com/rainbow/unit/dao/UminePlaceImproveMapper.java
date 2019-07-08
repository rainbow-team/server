package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.UminePlaceImprove;

import java.util.List;
import java.util.Map;

public interface UminePlaceImproveMapper extends MyMapper<UminePlaceImprove> {

    List<UminePlaceImprove> getUminePlaceImproveList(Map<String,Object> map);
}