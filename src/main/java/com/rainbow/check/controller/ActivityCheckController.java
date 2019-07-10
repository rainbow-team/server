package com.rainbow.check.controller;


import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.EquipCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.check.service.EquipCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 13260 on 2019/5/11.
 * 核活动及其他审评信息管理
 */
@RestController
@RequestMapping("activitycheck")
public class ActivityCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ActivityCheckService activityCheckService;

    /**
     * 添加核活动及其他审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/addActivityCheck")
    public ResponseBo add(@RequestBody ActivityCheck activityCheck) {
        int result = activityCheckService.addActivityCheck(activityCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核活动及其他审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyActivityCheck")
    public ResponseBo modify(@RequestBody ActivityCheck activityCheck) {

        int result = activityCheckService.modifyActivityCheck(activityCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核活动及其他审评信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getActivityCheckList")
    public ResponseBo getActivityCheckList(@RequestBody Page page) {

        return activityCheckService.getActivityCheckList(page);
    }

    /**
     * 获取核活动及其他审评信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geActivityCheckById")
    public ResponseBo getActivityCheckById(String id) {
        return activityCheckService.getActivityCheckById(id);
    }

    /**
     * 删除核活动及其他审评信息信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteActivityCheckById")
    public ResponseBo deleteActivityCheckById(String id) {
        if (id != null) {
            int result = activityCheckService.deleteActivityCheckById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }
}
