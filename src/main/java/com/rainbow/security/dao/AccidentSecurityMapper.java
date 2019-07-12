package com.rainbow.security.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.domain.FacSecurity;

import java.util.List;
import java.util.Map;

public interface AccidentSecurityMapper extends MyMapper<AccidentSecurity> {

    List<AccidentSecurity> getAccidentSecurityList(Map<String,Object> map);

    AccidentSecurity getAccidentSecurityById(String id);
}