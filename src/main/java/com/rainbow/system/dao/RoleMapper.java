package com.rainbow.system.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.system.domain.Role;
import com.rainbow.system.domain.extend.RoleWithMenu;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends MyMapper<Role> {

    List<Role> findUserRole(String userName);

    List<RoleWithMenu> findById(Long roleId);

    RoleWithMenu getRoleById(String roldId);

    List<Role> getRoleList(Map<String,Object> map);
}