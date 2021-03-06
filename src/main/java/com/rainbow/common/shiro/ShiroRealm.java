package com.rainbow.common.shiro;

import com.rainbow.system.domain.SystemMenu;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.system.service.MenuService;
import com.rainbow.system.service.RoleService;
import com.rainbow.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/4/30 11:35
 * @Description:
 **/

public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
    * @Description: 用于判断是否有权限登录
    * @Param: [authenticationToken]
    * @return: org.apache.shiro.authc.AuthenticationInfo
    * @Author: deepblue
    * @Date: 2019/4/30
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        // 通过用户名到数据库查询用户信息
        SystemUser user = this.userService.findUserByUsername(userName);

        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }

        return new SimpleAuthenticationInfo(user, password, getName());
    }


    /**
    * @Description: 用于判断有哪些权限
    * @Param: [principalCollection]
    * @return: org.apache.shiro.authz.AuthorizationInfo
    * @Author: deepblue
    * @Date: 2019/4/30
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //User user = (User) SecurityUtils.getSubject().getPrincipal();
        //String userName = user.getUsername();
        String userName=null;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
       // List<Role> roleList = this.roleService.findUserRole(userName);
       // Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        //simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<SystemMenu> permissionList = this.menuService.findUserPermissions(userName);
       // Set<String> permissionSet = permissionList.stream().map(SystemMenu::getPerms).collect(Collectors.toSet());
      //  simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }


}
