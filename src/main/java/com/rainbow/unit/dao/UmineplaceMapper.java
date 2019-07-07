package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.Umine;
import com.rainbow.unit.domain.Umineplace;

import java.util.List;
import java.util.Map;

public interface UmineplaceMapper extends MyMapper<Umineplace> {
    List<Umineplace> getUmineplaceList(Map<String, Object> map);

    Umineplace getUmineplaceById(String id);

    int getUminePlaceSumByUmineId(String umineId);
}