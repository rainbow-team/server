package com.rainbow.unit.dao;

import java.util.List;
import java.util.Map;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.UnitAddress;

public interface UnitAddressMapper extends MyMapper<UnitAddress> {

    List<UnitAddress> getUnitAddressList(Map<String, Object> map);

    UnitAddress getUnitAddressById(String id);

    int deleteUnitAddressById(String id);

    // 根据核设备单位的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getUnitAddressRelationCount(String id);

    List<UnitAddress> getUnitAddressListByProvince(String province);

    List<Map<String, String>> getChinaMapData();
}