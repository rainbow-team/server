package com.rainbow.security.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;

import java.util.List;
import java.util.Map;

public interface UminePlaceSecurityMapper extends MyMapper<UminePlaceSecurity> {

    List<UminePlaceSecurity> getUminePlaceSecurityList(Map<String,Object> map);

    UminePlaceSecurity getUminePlaceSecurityById(String id);
}