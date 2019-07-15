package com.rainbow.system.service.impl;

import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.system.dao.UserRoleMapper;
import com.rainbow.system.domain.Role;
import com.rainbow.system.domain.RoleMenu;
import com.rainbow.system.domain.SystemMenu;
import com.rainbow.system.domain.UserRole;
import com.rainbow.system.domain.extend.UserWithRole;
import com.rainbow.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/4/30 09:39
 * @Description:
 **/
@Service("userRoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public void deleteUserRoleByRoleId(String roleId) {
        userRoleMapper.deleteUserRoleByRoleId(roleId);
    }

    @Override
    public void insertUserRoleByRole(UserWithRole userWithRole) {
        if ((userWithRole.getRoleList() != null) && (userWithRole.getRoleList().size() > 0)) {
            for (Role role : userWithRole.getRoleList()) {
                UserRole userRole = new UserRole();
                userRole.setId(GuidHelper.getGuid());
                userRole.setUserId(userWithRole.getId());
                userRole.setRoleId(role.getId());
                userRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    public void deleteUserRoleByUserId(String userId) {
        userRoleMapper.deleteUserRoleByUserId(userId);
    }
}