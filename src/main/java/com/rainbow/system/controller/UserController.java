package com.rainbow.system.controller;

import com.rainbow.common.annotation.Log;
import com.rainbow.common.controller.BaseController;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.system.domain.SystemMenu;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.system.domain.extend.UserWithRole;
import com.rainbow.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/4/30 09:45
 * @Description:
 **/
@RestController
@RequestMapping("user")
public class UserController extends BaseController{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ON = "on";

    @Autowired
    private UserService userService;

 /*   @RequestMapping("user")
    @RequiresPermissions("user:list")
    public String index(Model model) {
        SystemUser user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "system/user/user";
    }*/

    //@Log("新增用户")
    //@RequiresPermissions("user:add")
    @RequestMapping("/addUser")
    @ResponseBody
    public ResponseBo addUser(UserWithRole user) {

        int result = this.userService.addUser(user);
        if (result == 1) {
            return ResponseBo.ok("新增用户成功！");
        } else {
            return ResponseBo.error("新增用户失败，请联系网站管理员！");
        }
    }

    //  @RequiresPermissions("user:delete")

    //@Log("删除用户")

    @RequestMapping("/deleteUsersByIds")
    @ResponseBody
    public ResponseBo deleteUsers(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            for (String id : ids) {
                userService.deleteUserById(id);
            }
            ResponseBo.ok("删除成功!");
        }
        return ResponseBo.error("删除失败，请重试!");
    }


    /**
     * 获取用户(包含他的角色)
     * @param userId
     * @return
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public ResponseBo getUserById(String userId) {
        try {
            UserWithRole user = this.userService.getUserWithRoleByUserId(userId);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    /**
     * 获取用户(不包含他的角色)
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
}
