package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.GroupExtend;

import java.util.List;
import java.util.Map;

public interface EquipDepartMapper extends MyMapper<EquipDepart> {

    List<EquipDepart> getEquipDepartList(Map<String, Object> map);

    EquipDepart getEquipDepartById(String id);

    int deleteEquipDepartById(String id);

    // 根据核设备单位的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getEquipDepartRelationCount(String id);

    String getEquipDepartIdByName(String equipDepartName);
}