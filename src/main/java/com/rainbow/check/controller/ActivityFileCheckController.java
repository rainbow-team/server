package com.rainbow.check.controller;


import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.check.service.ActivityFileCheckService;
import com.rainbow.check.service.FacFileCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核活动及其他审评文件信息管理
 */
@RestController
@RequestMapping("activityfilecheck")
    public class ActivityFileCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ActivityFileCheckService activityFileCheckService;

    /**
     * 添加核活动及其他审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/addActivityFileCheck")
    public ResponseBo add(@RequestBody ActivityFileCheck ActivityFileCheck) {
        int result = activityFileCheckService.addActivityFileCheck(ActivityFileCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核活动及其他审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyActivityFileCheck")
    public ResponseBo modify(@RequestBody ActivityFileCheck ActivityFileCheck) {

        int result = activityFileCheckService.updateAll(ActivityFileCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取核活动及其他审评文件信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getActivityFileCheckList")
    public ResponseBo getActivityFileCheckList(@RequestBody Page page) {

        return activityFileCheckService.getActivityFileCheckList(page);
    }

    /**
     * 获取核活动及其他审评文件详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getActivityFileCheckById")
    public ResponseBo getActivityFileCheckById(String id) {
        return activityFileCheckService.getActivityFileCheckById(id);
    }

    /**
     * 删除核活动及其他审评文件信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteActivityFileCheckByIds")
    public ResponseBo deleteActivityFileCheckByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            activityFileCheckService.batchDelete(ids, "id", ActivityFileCheck.class);
        }
        return ResponseBo.ok();
    }
}
