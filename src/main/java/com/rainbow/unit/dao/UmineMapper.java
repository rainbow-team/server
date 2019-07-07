package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.ServiceDepartExtend;
import com.rainbow.unit.domain.Umine;
import com.rainbow.unit.domain.UmineExtend;

import java.util.List;
import java.util.Map;

public interface UmineMapper extends MyMapper<Umine> {

    List<Umine> getUmineList(Map<String,Object> map);

    UmineExtend getUmineById(String umineId);

    int getSumByGroupId(String groupId);
}