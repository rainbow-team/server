package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.ServiceDepartExtend;
import com.rainbow.unit.domain.Umine;
import com.rainbow.unit.domain.UmineExtend;

import java.util.List;
import java.util.Map;

public interface UmineMapper extends MyMapper<Umine> {

    List<Umine> getUmineList(Map<String, Object> map);

    UmineExtend getUmineById(String id);

    int getSumByGroupId(String groupId);

    int deleteUmineById(String id);

    // 根据铀矿冶的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getUmineRelationCount(String id);

    String getUmineIdByName(String name);
}