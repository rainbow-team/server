package com.rainbow.unit.service;

import java.util.List;
import java.util.Map;

import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.UnitAddress;

/**
 * @Author:idealks
 * @Date:2019年8月21日16:37:27
 * @Description:
 **/
public interface UnitAddressService extends IService<UnitAddress> {

    int addUnitAddress(UnitAddress umine);

    int modifyUnitAddress(UnitAddress umine);

    int deleteUnitAddressById(String id);

    List<UnitAddress> getUnitAddressListByProvince(String province);

    List<Map<String, String>> getChinaMapData();
}
