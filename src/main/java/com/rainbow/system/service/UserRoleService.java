package com.rainbow.system.service;

import com.rainbow.common.service.IService;
import com.rainbow.system.domain.UserRole;

/**
 * @Author:deepblue
 * @Date:2019/4/30 09:38
 * @Description:
 **/
public interface UserRoleService extends IService<UserRole> {

    void deleteUserRolesByRoleId(String roleIds);

    void deleteUserRolesByUserId(String userIds);
}