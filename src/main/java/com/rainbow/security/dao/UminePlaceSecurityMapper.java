package com.rainbow.security.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.domain.extend.UminePlaceSecurityExtend;

import java.util.List;
import java.util.Map;

public interface UminePlaceSecurityMapper extends MyMapper<UminePlaceSecurity> {

    List<UminePlaceSecurityExtend> getUminePlaceSecurityList(Map<String,Object> map);

    UminePlaceSecurityExtend getUminePlaceSecurityById(String id);
}