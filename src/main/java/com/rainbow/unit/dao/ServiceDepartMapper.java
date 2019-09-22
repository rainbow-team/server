package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.ServiceDepart;
import com.rainbow.unit.domain.ServiceDepartExtend;
import com.rainbow.unit.domain.Umine;

import java.util.List;
import java.util.Map;

public interface ServiceDepartMapper extends MyMapper<ServiceDepart> {

    List<ServiceDepartExtend> getServiceDepartList(Map<String, Object> map);

    ServiceDepartExtend getServiceDepartByServiceId(String id);

    int getSumByGroupId(String groupId);

    int deleteServiceDepartById(String id);

    // 根据核设施营运单位的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getServiceDepartRelationCount(String id);

    int getServiceDepartByName(String name);

    String getServiceDepartIdByName(String name);

}