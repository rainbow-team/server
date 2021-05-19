package com.rainbow.system.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.QueryRequest;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.system.domain.extend.UserWithRole;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @Author:liuhao
 * @Date:2019/4/29 14:48
 * @Description:
 **/
public interface UserService extends IService<SystemUser> {
    /*    *//**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     *//*
     * User findByName(String userName);
     *
     */

    /**
     * 更新用户的最后登录时间
     *
     * @param userName
     *//*
     * void updateLoginTime(String userName);
     *
     * UserWithRole findById(Long userId);
     *
     *
     * @Cacheable(key = "#p0.toString() + (#p1 != null ? #p1.toString() : '')")
     * List<User> findUserWithDept(User user, QueryRequest request);
     *
     * @CacheEvict(key = "#p0", allEntries = true) void registUser(User user);
     *
     * void updateTheme(String theme, String userName);
     *
     *
     *
     * @CacheEvict(key = "#p0", allEntries = true) void updateUser(User user, Long[]
     * roles);
     */

    // @CacheEvict(allEntries = true)
    int addUser(UserWithRole user);

    int modifyUser(UserWithRole userWithRole);

    // @CacheEvict(key = "#p0", allEntries = true)
    int deleteUserById(String id);

    UserWithRole getUserWithRoleByUserId(String id);

    List<String> getAllPermissionByUserId(String id);

    /*
     * void updatePassword(String password);
     *
     * User findUserProfile(User user);
     *
     * void updateUserProfile(User user);
     */
    SystemUser login(Map<String, String> map);

    SystemUser findUserByUsername(String username);

    ResponseBo getUserList(Page page);

    int changePassword(SystemUser user);

    List<SystemUser> getAllUser();

}
