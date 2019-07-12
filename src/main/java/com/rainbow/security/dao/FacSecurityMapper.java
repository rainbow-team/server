package com.rainbow.security.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.security.domain.FacSecurity;

import java.util.List;
import java.util.Map;

public interface FacSecurityMapper extends MyMapper<FacSecurity> {

    List<FacSecurity> getFacSecurityList(Map<String,Object> map);

    FacSecurity getFacSecurityById(String id);
}