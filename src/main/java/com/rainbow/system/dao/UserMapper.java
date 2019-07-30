package com.rainbow.system.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.system.domain.extend.UserWithRole;

import java.util.List;
import java.util.Map;

/**
 * @Author:liuhao
 * @Date:2019/4/29 13:54
 * @Description:
 **/
public interface UserMapper extends MyMapper<SystemUser> {

    /*
     * List<SystemUser> findSystemUserWithDept(SystemUser user);
     * 
     * //List<SystemUserWithRole> findSystemUserWithRole(Long userId);
     * 
     * SystemUser findSystemUserProfile(SystemUser user);
     * 
     * 
     * 
     * String getSystemUserNameById(String id);
     */

    SystemUser login(Map<String, String> map);

    UserWithRole getUserDetailByUserId(String userId);

    List<String> getPermissionByUserId(String userId);

    SystemUser findByName(String username);

    String getUserNameById(String userId);

    SystemUser findUserByUsername(String username);

    List<SystemUser> getUserList(Map<String, Object> map);
}