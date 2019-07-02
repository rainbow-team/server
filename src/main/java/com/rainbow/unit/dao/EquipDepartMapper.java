package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Fac;

import java.util.List;
import java.util.Map;

public interface EquipDepartMapper extends MyMapper<EquipDepart> {

    List<EquipDepart> getEquipDepartList(Map<String,Object> map);
}