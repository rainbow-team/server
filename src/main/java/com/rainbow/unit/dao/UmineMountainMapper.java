package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.UmineMountain;
import com.rainbow.unit.domain.Umineplace;

import java.util.List;
import java.util.Map;

public interface UmineMountainMapper extends MyMapper<UmineMountain> {

    List<UmineMountain> getUmineplaceList(Map<String,Object> map);

    int getMountainSumByUmineId(String umineId);

}