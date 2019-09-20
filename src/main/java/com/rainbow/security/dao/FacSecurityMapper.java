package com.rainbow.security.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.extend.FacSecurityExtend;

import java.util.List;
import java.util.Map;

public interface FacSecurityMapper extends MyMapper<FacSecurity> {

    List<FacSecurityExtend> getFacSecurityList(Map<String,Object> map);

    FacSecurityExtend getFacSecurityById(String id);
}