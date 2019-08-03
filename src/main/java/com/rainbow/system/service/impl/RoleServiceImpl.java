package com.rainbow.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.domain.Welder;
import com.rainbow.system.dao.RoleMapper;
import com.rainbow.system.dao.RoleMenuMapper;
import com.rainbow.system.domain.Role;
import com.rainbow.system.domain.extend.RoleWithMenu;
import com.rainbow.system.service.RoleMenuServie;
import com.rainbow.system.service.RoleService;
import com.rainbow.system.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/4/30 13:46
 * @Description:
 **/
@Service("roleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleMenuServie roleMenuServie;

    @Autowired
    UserRoleService userRoleService;

    @Override
    @Transactional
    public int addRole(RoleWithMenu roleWithMenu) {
        String roleId = GuidHelper.getGuid();
        roleWithMenu.setId(roleId);
        try {
            roleMapper.insert(roleWithMenu);
            roleMenuServie.insetRoleMenuByRole(roleWithMenu);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int modifyRole(RoleWithMenu roleWithMenu) {
        try {
            roleMapper.updateByPrimaryKey(roleWithMenu);
            roleMenuServie.deleteRoleMenusByRoleId(roleWithMenu.getId());
            roleMenuServie.insetRoleMenuByRole(roleWithMenu);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public ResponseBo getRoleList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Role> list = roleMapper.getRoleList(map);

        PageInfo<Role> pageInfo = new PageInfo<Role>(list);

        PagingEntity<Role> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public int deleteRoleById(String id) {
        try {
            roleMapper.deleteByPrimaryKey(id);
            roleMenuServie.deleteRoleMenusByRoleId(id);
            userRoleService.deleteUserRoleByRoleId(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public RoleWithMenu getRoleById(String id) {
        RoleWithMenu result = roleMapper.getRoleById(id);
        return result;
    }
}
