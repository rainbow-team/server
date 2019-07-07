package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.ServiceDepart;
import com.rainbow.unit.domain.ServiceDepartExtend;
import com.rainbow.unit.domain.Umine;

import java.util.List;
import java.util.Map;

public interface ServiceDepartMapper extends MyMapper<ServiceDepart> {

    List<ServiceDepart> getServiceDepartList(Map<String,Object> map);

    ServiceDepartExtend getServiceDepartByServiceId(String serviceId);

    int getSumByGroupId(String groupId);
}