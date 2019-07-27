package com.rainbow.permit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.permit.domain.EquipPermit;

import java.util.List;
import java.util.Map;

public interface EquipPermitMapper extends MyMapper<EquipPermit> {

    List<EquipPermit> getEquipPermitList(Map<String,Object> map);

    EquipPermit getEquipPermitById(String id);

    int getEquipNum();
}