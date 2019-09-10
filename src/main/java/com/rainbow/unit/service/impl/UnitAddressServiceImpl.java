package com.rainbow.unit.service.impl;

import java.util.List;
import java.util.Map;

import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.UnitAddressMapper;
import com.rainbow.unit.domain.UnitAddress;
import com.rainbow.unit.service.UnitAddressService;
import com.rainbow.unit.service.UnitHotRegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @param <UnitAddressMapper>
 * @Author:idealks
 * @Date:2019/8/15 13:11
 * @Description:
 **/
@Service("unitaddressservice")
public class UnitAddressServiceImpl extends BaseService<UnitAddress> implements UnitAddressService {

    @Autowired
    UnitAddressMapper mapper;

    @Autowired
    FileInfoService fileInfoService;
    @Autowired
    UnitHotRegionService unitHotRegionService;

    @Override
    public int addUnitAddress(UnitAddress address) {
        address.setId(GuidHelper.getGuid());

        fileInfoService.updateFileInfoByIds(address.getAttachmentList(), address.getId());
        return mapper.insert(address);
    }

    @Override
    public int modifyUnitAddress(UnitAddress address) {
        fileInfoService.updateFileInfoByIds(address.getAttachmentList(), address.getId());
        return mapper.updateByPrimaryKey(address);
    }

    @Override
    public int deleteUnitAddressById(String id) {
        unitHotRegionService.deleteUnitHotRegionsByAddressId(id);
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UnitAddress> getUnitAddressListByProvince(String province) {
        return mapper.getUnitAddressListByProvince(province);
    }

    @Override
    public List<Map<String, String>> getChinaMapData() {
        return mapper.getChinaMapData();
    }
}