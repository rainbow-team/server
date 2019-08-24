package com.rainbow.unit.dao;

import java.util.List;
import java.util.Map;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.UnitHotRegion;

public interface UnitHotRegionMapper extends MyMapper<UnitHotRegion> {

    List<UnitHotRegion> getUnitHotRegionList(Map<String, Object> map);

    UnitHotRegion getUnitHotRegionById(String id);

    int deleteUnitHotRegionById(String id);

    // 根据核设备单位的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getUnitHotRegionRelationCount(String id);

    int insertRegionsBatch(List<UnitHotRegion> regions);

    List<UnitHotRegion> getUnitHotRegionListByUnitId(String unitId);
}