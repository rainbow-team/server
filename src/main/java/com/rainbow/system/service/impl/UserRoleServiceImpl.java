package com.rainbow.system.service.impl;

import com.rainbow.common.service.impl.BaseService;
import com.rainbow.system.domain.UserRole;
import com.rainbow.system.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

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

    @Override
    @Transactional
    public void deleteUserRolesByRoleId(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(","));
        this.batchDelete(list, "roleId", UserRole.class);
    }

    @Override
    @Transactional
    public void deleteUserRolesByUserId(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        this.batchDelete(list, "userId", UserRole.class);
    }
}
