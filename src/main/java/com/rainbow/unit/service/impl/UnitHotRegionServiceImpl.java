package com.rainbow.unit.service.impl;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
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

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUnitHotRegion(UnitHotRegion unitHotRegion) {
        unitHotRegion.setId(GuidHelper.getGuid());
        fileInfoService.updateFileInfoByIds(unitHotRegion.getAttachmentList(), unitHotRegion.getId());
        return mapper.insert(unitHotRegion);
    }

    @Override
    public int modifyUnitHotRegion(UnitHotRegion unitHotRegion) {
        fileInfoService.updateFileInfoByIds(unitHotRegion.getAttachmentList(), unitHotRegion.getId());
        return mapper.updateByPrimaryKey(unitHotRegion);
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
    public List<UnitHotRegion> getUnitHotRegionListByAddressId(String addressId) {
        return mapper.getUnitHotRegionListByAddressId(addressId);
    }

    @Override
    public ResponseBo getUnitHotRegionList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UnitHotRegion> list = mapper.getUnitHotRegionList(map);

        PageInfo<UnitHotRegion> pageInfo = new PageInfo<UnitHotRegion>(list);

        PagingEntity<UnitHotRegion> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public UnitHotRegion getUnitHotRegionById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteUnitHotRegionsByAddressId(String id) {

        return mapper.deleteUnitHotRegionsByAddressId(id);
    }
}