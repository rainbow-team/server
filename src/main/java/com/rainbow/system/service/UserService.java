package com.rainbow.system.service;

import com.rainbow.common.domain.QueryRequest;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.system.domain.User;
import com.rainbow.system.domain.UserWithRole;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @Author:liuhao
 * @Date:2019/4/29 14:48
 * @Description:
 **/
public interface UserService extends IService<User>{
    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    User findByName(String userName);

    /**
     * 更新用户的最后登录时间
     * @param userName
     */
    void updateLoginTime(String userName);

    UserWithRole findById(Long userId);



    @Cacheable(key = "#p0.toString() + (#p1 != null ? #p1.toString() : '')")
    List<User> findUserWithDept(User user, QueryRequest request);

    @CacheEvict(key = "#p0", allEntries = true)
    void registUser(User user);

    void updateTheme(String theme, String userName);

    @CacheEvict(allEntries = true)
    void addUser(User user, Long[] roles);

    @CacheEvict(key = "#p0", allEntries = true)
    void updateUser(User user, Long[] roles);

    @CacheEvict(key = "#p0", allEntries = true)
    void deleteUsers(String userIds);



    void updatePassword(String password);

    User findUserProfile(User user);

    void updateUserProfile(User user);

    ResponseBo login(Map<String,String> map );

}
