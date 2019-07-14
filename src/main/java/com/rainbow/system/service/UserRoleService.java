package com.rainbow.system.service;

import com.rainbow.common.service.IService;
import com.rainbow.system.domain.UserRole;
import com.rainbow.system.domain.extend.UserWithRole;

/**
 * @Author:deepblue
 * @Date:2019/4/30 09:38
 * @Description:
 **/
public interface UserRoleService extends IService<UserRole> {

    //根据角色名称删除用户角色关联信息
    void deleteUserRoleByRoleId(String roleId);

    //根据用户的信息插入用户的角色关联关系
    void insertUserRoleByRole(UserWithRole userWithRole);


    void deleteUserRolesByUserId(String userId);
}