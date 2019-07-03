package com.rainbow.system.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.system.domain.User;
import com.rainbow.system.domain.UserWithRole;

import java.util.List;
import java.util.Map;

/**
 * @Author:liuhao
 * @Date:2019/4/29 13:54
 * @Description:
 **/
public interface UserMapper extends MyMapper<User> {

    List<User> findUserWithDept(User user);

    List<UserWithRole> findUserWithRole(Long userId);

    User findUserProfile(User user);

    Map<String,Object> login(Map<String,String> map );

    String getUserNameById(String id);
}