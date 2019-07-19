package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.Umine;
import com.rainbow.unit.domain.Umineplace;

import java.util.List;
import java.util.Map;

public interface UmineplaceMapper extends MyMapper<Umineplace> {

    int getUminePlaceSumByUmineId(String umineId);

    List<Umineplace> getUmineplaceList(Map<String, Object> map);

    Umineplace getUmineplaceById(String id);

    int deleteUmineplaceById(String id);

    //根据铀尾矿(渣)库的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getUmineplaceRelationCount(String id);

    List<Umineplace> getUmineplaceListByUmineId(String umineId);
}