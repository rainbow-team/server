package com.rainbow.system.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.system.domain.Role;
import com.rainbow.system.domain.RoleWithMenu;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {

    List<Role> findUserRole(String userName);

    List<RoleWithMenu> findById(Long roleId);
}