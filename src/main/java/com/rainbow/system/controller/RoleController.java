package com.rainbow.system.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.Welder;
import com.rainbow.system.domain.Role;
import com.rainbow.system.domain.extend.RoleWithMenu;
import com.rainbow.system.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/4/30 09:45
 * @Description:
 **/
@RestController
@RequestMapping("role")
public class RoleController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色信息
     *
     * @param
     * @return
     */
    @PostMapping("/addRole")
    @SystemLog(description="添加角色信息")
    public ResponseBo add(@RequestBody RoleWithMenu roleWithMenu) {
        int result = roleService.addRole(roleWithMenu);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改角色信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyRole")
    @SystemLog(description="修改角色信息")
    public ResponseBo modify(@RequestBody RoleWithMenu roleWithMenu) {

        int result = roleService.modifyRole(roleWithMenu);

        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取角色列表
     * 
     * @param page
     * @return
     */
    @PostMapping("/getRoleList")
    public ResponseBo getWelderList(@RequestBody Page page) {
        return roleService.getRoleList(page);
    }

    /*
     * 获取角色详情
     * 
     * @param id
     * 
     * @returnRole
     */
    @GetMapping("/getRoleById")
    public ResponseBo getById(String id) {
        RoleWithMenu roleWithMenu = roleService.getRoleById(id);
        return ResponseBo.ok(roleWithMenu);
    }

    /**
     * 删除角色信息
     * 
     * @param ids
     * @return
     */
    @PostMapping("/deleteRoleByIds")
    @SystemLog(description="删除角色信息")
    public ResponseBo deleteRoleByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            for (String id : ids) {
                roleService.deleteRoleById(id);
            }
            return ResponseBo.ok("删除成功!");
        }
        return ResponseBo.error("删除失败，请重试!");
    }

    @PostMapping("/getAllRoles")
    public ResponseBo getAllRoles() {

        List<Role> result = roleService.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
