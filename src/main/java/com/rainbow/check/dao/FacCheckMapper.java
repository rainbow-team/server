package com.rainbow.check.dao;

import com.rainbow.check.domain.FacCheck;
import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.Fac;

import java.util.List;
import java.util.Map;

public interface FacCheckMapper extends MyMapper<FacCheck> {

    FacCheck getFacCheckById(String id);

    List<FacCheck> getFacCheckList(Map<String,Object> map);

    int deleteFacCheckById(String id);

    //根据核设施审评的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getFacCheckRelationCount(String id);

}