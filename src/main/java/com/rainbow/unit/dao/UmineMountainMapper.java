package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.UmineMountain;
import com.rainbow.unit.domain.Umineplace;

import java.util.List;
import java.util.Map;

public interface UmineMountainMapper extends MyMapper<UmineMountain> {

    int getMountainSumByUmineId(String umineId);

    List<UmineMountain> getUmineMountainList(Map<String,Object> map);

    UmineMountain getUmineMountainById(String id);

    int deleteUmineMountainById(String id);

    //根据铀矿山的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getUmineMountainRelationCount(String id);
}