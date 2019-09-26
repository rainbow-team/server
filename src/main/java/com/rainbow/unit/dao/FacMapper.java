package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.FacExtend;

import java.util.List;
import java.util.Map;

public interface FacMapper extends MyMapper<Fac> {

    int getFacNumByServiceId(String serviceId);

    List<FacExtend> getFacList(Map<String, Object> map);

    Fac getFacById(String id);

    int deleteFacById(String id);

    // 根据核设施的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getFacRelationCount(String id);

    List<Fac> getFacListByServiceId(String serviceId);

    int getFacCount();

    String getFacIdByName(String name);

    int verifyDuplication(Map<String, Object> params);
}