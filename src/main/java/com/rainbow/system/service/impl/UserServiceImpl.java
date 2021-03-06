package com.rainbow.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.system.dao.UserMapper;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.system.domain.extend.SystemUserExtend;
import com.rainbow.system.domain.extend.UserWithRole;
import com.rainbow.system.service.UserRoleService;
import com.rainbow.system.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:liuhao
 * @Date:2019/4/29 14:50
 * @Description:
 **/
@Repository("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<SystemUser> implements UserService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public int addUser(UserWithRole user) {
        user.setId(GuidHelper.getGuid());
        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        try {
            userMapper.insert(user);
            userRoleService.insertUserRoleByRole(user);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int modifyUser(UserWithRole userWithRole) {
        try {
            userWithRole.setModifyDate(new Date());
            userMapper.updateByPrimaryKey(userWithRole);
            userRoleService.deleteUserRoleByUserId(userWithRole.getId());
            userRoleService.insertUserRoleByRole(userWithRole);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int deleteUserById(String id) {
        try {
            userMapper.deleteByPrimaryKey(id);
            userRoleService.deleteUserRoleByUserId(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public UserWithRole getUserWithRoleByUserId(String userId) {
        return userMapper.getUserDetailByUserId(userId);
    }

    @Override
    public List<String> getAllPermissionByUserId(String userId) {
        List<String> result = userMapper.getPermissionByUserId(userId);
        return result;
    }

    @Override
    public ResponseBo getUserList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SystemUserExtend> list = userMapper.getUserList(map);

        PageInfo<SystemUserExtend> pageInfo = new PageInfo<SystemUserExtend>(list);

        PagingEntity<SystemUserExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    /*
     * @Autowired private SystemUserRoleMapper userRoleMapper;
     *//*
        * 
        * 
        * @Autowired private UserRoleService userRoleService;
        * 
        * 
        * @Override public SystemUser findByName(String userName) { Example example =
        * new Example(SystemUser.class);
        * example.createCriteria().andCondition("lower(username)=",
        * userName.toLowerCase()); List<SystemUser> list =
        * this.selectByExample(example); return list.isEmpty() ? null : list.get(0); }
        * 
        * 
        * @Override
        * 
        * @Transactional public void updateLoginTime(String userName) { Example example
        * = new Example(SystemUser.class);
        * example.createCriteria().andCondition("lower(username)=",
        * userName.toLowerCase()); SystemUser user = new SystemUser();
        * user.setLastLoginTime(new Date());
        * this.userMapper.updateByExampleSelective(user, example); }
        * 
        * 
        * @Override public UserWithRole findById(Long userId) { List<UserWithRole> list
        * = this.userMapper.findUserWithRole(userId); List<Long> roleList =
        * list.stream().map(UserWithRole::getRoleId).collect(Collectors.toList()); if
        * (list.isEmpty()) { return null; } UserWithRole userWithRole = list.get(0);
        * userWithRole.setRoleIds(roleList); return userWithRole; }
        * 
        * 
        * 
        * @Override public List<User> findUserWithDept(User user, QueryRequest request)
        * { try { return this.userMapper.findUserWithDept(user); } catch (Exception e)
        * { log.error("error", e); return new ArrayList<>(); } }
        * 
        * @Override
        * 
        * @Transactional public void registUser(User user) { user.setCrateTime(new
        * Date()); user.setTheme(User.DEFAULT_THEME);
        * user.setAvatar(User.DEFAULT_AVATAR); user.setSsex(User.SEX_UNKNOW);
        * user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        * this.save(user); UserRole ur = new UserRole();
        * ur.setUserId(user.getUserId()); ur.setRoleId(3L);
        * this.userRoleMapper.insert(ur); }
        * 
        * @Override
        * 
        * @Transactional public void updateTheme(String theme, String userName) {
        * Example example = new Example(User.class);
        * example.createCriteria().andCondition("username=", userName); User user = new
        * User(); user.setTheme(theme); this.userMapper.updateByExampleSelective(user,
        * example); }
        * 
        * @Override
        * 
        * @Transactional public void addUser(User user, Long[] roles) {
        * user.setCrateTime(new Date()); user.setTheme(User.DEFAULT_THEME);
        * user.setAvatar(User.DEFAULT_AVATAR);
        * user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        * this.save(user); setUserRoles(user, roles); }
        * 
        * private void setUserRoles(User user, Long[] roles) {
        * Arrays.stream(roles).forEach(roleId -> { UserRole ur = new UserRole();
        * ur.setUserId(user.getUserId()); ur.setRoleId(roleId);
        * this.userRoleMapper.insert(ur); }); }
        * 
        * @Override
        * 
        * @Transactional public void updateUser(User user, Long[] roles) {
        * user.setPassword(null); user.setUsername(null); user.setModifyTime(new
        * Date()); this.updateNotNull(user); Example example = new
        * Example(UserRole.class); example.createCriteria().andCondition("user_id=",
        * user.getUserId()); this.userRoleMapper.deleteByExample(example);
        * setUserRoles(user, roles); }
        */

    /*
     * @Override public void updatePassword(String password) { User user = (User)
     * SecurityUtils.getSubject().getPrincipal(); Example example = new
     * Example(User.class); example.createCriteria().andCondition("username=",
     * user.getUsername()); String newPassword =
     * MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
     * user.setPassword(newPassword); this.userMapper.updateByExampleSelective(user,
     * example); }
     * 
     * @Override public User findUserProfile(User user) { return
     * this.userMapper.findUserProfile(user); }
     * 
     * @Override public void updateUserProfile(User user) { user.setUsername(null);
     * user.setPassword(null); if (user.getDeptId() == null) { user.setDeptId(0L); }
     * this.updateNotNull(user); }
     */
    @Override
    public SystemUser login(Map<String, String> map) {

        return userMapper.login(map);

    }

    @Override
    public SystemUser findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public int changePassword(SystemUser user) {
        return userMapper.changePassword(user);
    }
}