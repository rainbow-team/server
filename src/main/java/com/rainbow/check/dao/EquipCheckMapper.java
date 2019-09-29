package com.rainbow.check.dao;

import com.rainbow.check.domain.EquipCheck;
import com.rainbow.check.domain.EquipCheckExtend;
import com.rainbow.check.domain.FacCheck;
import com.rainbow.common.config.MyMapper;

import java.util.List;
import java.util.Map;


public interface EquipCheckMapper extends MyMapper<EquipCheck> {

    EquipCheck getEquipCheckById(String id);

    List<EquipCheckExtend> getEquipCheckList(Map<String,Object> map);

    int deleteEquipCheckById(String id);

    //根据核安全设备审评的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getEquipCheckRelationCount(String id);
}