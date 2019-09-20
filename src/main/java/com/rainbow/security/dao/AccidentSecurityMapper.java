package com.rainbow.security.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.extend.AccidentSecurityExtend;

import java.util.List;
import java.util.Map;

public interface AccidentSecurityMapper extends MyMapper<AccidentSecurity> {

    List<AccidentSecurityExtend> getAccidentSecurityList(Map<String,Object> map);

    AccidentSecurityExtend getAccidentSecurityById(String id);
}