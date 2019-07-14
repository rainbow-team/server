package com.rainbow.system.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.system.domain.UserRole;

public interface UserRoleMapper extends MyMapper<UserRole> {

    int deleteUserRoleByRoleId(String roleId);

    int deleteUserRoleByUserId(String userId);
}