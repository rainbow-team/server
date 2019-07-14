package com.rainbow.system.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.system.domain.Role;
import com.rainbow.system.domain.extend.RoleWithMenu;

/**
 * @Author:deepblue
 * @Date:2019/4/30 13:45
 * @Description:
 **/
public interface RoleService  extends IService<Role> {

    int addRole(RoleWithMenu roleWithMenu);

    int modifyRole(RoleWithMenu roleWithMenu);

    int deleteRoleById(String id);

    RoleWithMenu getRoleById(String id);

    ResponseBo getRoleList(Page page);

/*    List<Role> findUserRole(String userName);

    List<Role> findAllRole(Role role);

    RoleWithMenu findRoleWithMenus(Long roleId);

    Role findByName(String roleName);

    void addRole(Role role, Long[] menuIds);

    void updateRole(Role role, Long[] menuIds);

    void deleteRoles(String roleIds);*/
}
