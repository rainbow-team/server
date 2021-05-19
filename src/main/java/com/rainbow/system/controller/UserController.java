package com.rainbow.system.controller;

import java.util.List;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.controller.BaseController;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.system.domain.extend.UserWithRole;
import com.rainbow.system.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:deepblue
 * @Date:2019/4/30 09:45
 * @Description:
 **/
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ON = "on";

    @Autowired
    private UserService userService;

    /*
     * @RequestMapping("user")
     *
     * @RequiresPermissions("user:list") public String index(Model model) {
     * SystemUser user = super.getCurrentUser(); model.addAttribute("user", user);
     * return "system/user/user"; }
     */

    // @Log("新增用户")
    // @RequiresPermissions("user:add")
    @PostMapping("/addUser")
    @SystemLog(description = "新增用户")
    public ResponseBo addUser(@RequestBody UserWithRole user) {

        int result = this.userService.addUser(user);
        if (result == 1) {
            return ResponseBo.ok("新增用户成功！");
        } else {
            return ResponseBo.error("新增用户失败，请联系网站管理员！");
        }
    }

    /**
     * 修改用户信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUser")
    @SystemLog(description = "修改用户信息")
    public ResponseBo modify(@RequestBody UserWithRole user) {
        int result = userService.modifyUser(user);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    // @RequiresPermissions("user:delete")

    // @Log("删除用户")

    @PostMapping("/deleteUsersByIds")
    @SystemLog(description = "删除用户")
    public ResponseBo deleteUsers(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            for (String id : ids) {
                userService.deleteUserById(id);
            }
            return ResponseBo.ok("删除成功!");
        }
        return ResponseBo.error("删除失败，请重试!");
    }

    /**
     * 获取用户(包含他的角色)
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserWithRoleByUserId")
    public ResponseBo getUserWithRoleByUserId(String id) {
        try {
            UserWithRole user = this.userService.getUserWithRoleByUserId(id);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    /**
     * 获取用户(不包含他的角色)
     *
     * @param userId
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ResponseBo getUserInfo(Long userId) {
        try {
            SystemUser user = this.userService.selectByKey(userId);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户信息失败！");
        }
    }

    /**
     * 获取用户所有权限
     *
     * @param userId
     * @return
     */
    @PostMapping("/getPermissionsByUserId")
    public ResponseBo getPermissionsByUserId(String userId) {
        try {
            List<String> result = userService.getAllPermissionByUserId(userId);
            return ResponseBo.ok(result);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    /**
     * 获取用户列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getUserList")
    public ResponseBo getUserList(@RequestBody Page page) {

        return userService.getUserList(page);
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @PostMapping("/changePassword")
    public ResponseBo changePassword(@RequestBody SystemUser user) {

        try {
            int result = userService.changePassword(user);
            if (result == 1) {
                return ResponseBo.ok("密码修改成功。");
            } else {
                return ResponseBo.error("密码修改失败！");
            }

        } catch (Exception e) {
            log.error("密码修改失败", e);
            return ResponseBo.error("密码修改失败！");
        }

    }

    @PostMapping("/getAllUser")
    public ResponseBo getAllUser() {
        return ResponseBo.ok(userService.getAllUser());
    }
}
