package com.rainbow.system.controller;

import com.rainbow.common.annotation.Log;
import com.rainbow.common.controller.BaseController;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.system.domain.User;
import com.rainbow.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:deepblue
 * @Date:2019/4/30 09:45
 * @Description:
 **/
@Controller
public class UserController extends BaseController{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ON = "on";

    @Autowired
    private UserService userService;

    @RequestMapping("user")
    @RequiresPermissions("user:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "system/user/user";
    }

    @Log("新增用户")
    @RequiresPermissions("user:add")
    @RequestMapping("user/add")
    @ResponseBody
    public ResponseBo addUser(User user, Long[] roles) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus())) {
                user.setStatus(User.STATUS_VALID);
            }
            else {
                user.setStatus(User.STATUS_LOCK);
            }
            this.userService.addUser(user, roles);
            return ResponseBo.ok("新增用户成功！");
        } catch (Exception e) {
            log.error("新增用户失败", e);
            return ResponseBo.error("新增用户失败，请联系网站管理员！");
        }
    }

    //  @RequiresPermissions("user:delete")

    //@Log("删除用户")

    @RequestMapping("user/delete")
    @ResponseBody
    public ResponseBo deleteUsers(String ids) {
        try {
            this.userService.deleteUsers(ids);
            return ResponseBo.ok("删除用户成功！");
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return ResponseBo.error("删除用户失败，请联系网站管理员！");
        }
    }


    /**
     * 获取用户(包含他的角色)
     * @param userId
     * @return
     */
    @RequestMapping("user/getUser")
    @ResponseBody
    public ResponseBo getUser(Long userId) {
        try {
            User user = this.userService.findById(userId);
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
    @RequestMapping("user/getUserInfo")
    @ResponseBody
    public ResponseBo getUserInfo(Long userId) {
        try {
            User user = this.userService.selectByKey(userId);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户信息失败！");
        }
    }
}
