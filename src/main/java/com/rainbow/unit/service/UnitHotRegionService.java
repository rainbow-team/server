package com.rainbow.unit.service;

import java.util.List;

import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.UnitHotRegion;

/**
 * @Author:idealks
 * @Date:2019å¹´8æœˆ21æ—¥16:37:27
 * @Description:
 **/
public interface UnitHotRegionService extends IService<UnitHotRegion> {

    int addUnitHotRegion(UnitHotRegion region);

    int modifyUnitHotRegion(UnitHotRegion region);

    int deleteUnitHotRegionById(String id);

    int insertRegionsBatch(List<UnitHotRegion> regions);

    List<UnitHotRegion> getUnitHotRegionListByUnitId(String unitId);
}
