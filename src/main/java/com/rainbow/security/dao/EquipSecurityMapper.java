package com.rainbow.security.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.security.domain.EquipSecurity;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.extend.EquipSecurityExtend;

import java.util.List;
import java.util.Map;

public interface EquipSecurityMapper extends MyMapper<EquipSecurity> {

    List<EquipSecurityExtend> getEquipSecurityList(Map<String,Object> map);

    EquipSecurityExtend getEquipSecurityById(String id);
}