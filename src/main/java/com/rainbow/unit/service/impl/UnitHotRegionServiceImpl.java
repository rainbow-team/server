package com.rainbow.unit.service.impl;

import java.util.List;

import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.UnitHotRegionMapper;
import com.rainbow.unit.domain.UnitHotRegion;
import com.rainbow.unit.service.UnitHotRegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @param <UnitHotRegionMapper>
 * @Author:idealks
 * @Date:2019/8/24 13:11
 * @Description:
 **/
@Service("unithotregionservice")
public class UnitHotRegionServiceImpl extends BaseService<UnitHotRegion> implements UnitHotRegionService {

    @Autowired
    UnitHotRegionMapper mapper;

    @Override
    public int addUnitHotRegion(UnitHotRegion address) {
        address.setId(GuidHelper.getGuid());
        return mapper.insert(address);
    }

    @Override
    public int modifyUnitHotRegion(UnitHotRegion address) {
        return mapper.updateByPrimaryKey(address);
    }

    @Override
    public int deleteUnitHotRegionById(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertRegionsBatch(List<UnitHotRegion> regions) {
        return mapper.insertRegionsBatch(regions);
    }

    @Override
    public List<UnitHotRegion> getUnitHotRegionListByUnitId(String unitId) {
        return mapper.getUnitHotRegionListByUnitId(unitId);
    }
}