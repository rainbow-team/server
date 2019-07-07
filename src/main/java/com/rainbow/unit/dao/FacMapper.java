package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.Fac;

import java.util.List;
import java.util.Map;

public interface FacMapper extends MyMapper<Fac> {

    List<Fac> getFacList(Map<String,Object> map);

    int getFacNumByServiceId(String serviceId);

    Fac getFacById(String id);
}