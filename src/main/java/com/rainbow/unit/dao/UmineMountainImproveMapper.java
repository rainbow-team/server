package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.UmineMountain;
import com.rainbow.unit.domain.UmineMountainImprove;

import java.util.List;
import java.util.Map;

public interface UmineMountainImproveMapper extends MyMapper<UmineMountainImprove> {

    List<UmineMountainImprove> getUmineMountainImproveList(Map<String,Object> map);

}