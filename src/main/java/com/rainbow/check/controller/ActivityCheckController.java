package com.rainbow.check.controller;


import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.EquipCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.check.service.EquipCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    @SystemLog(description="添加核活动及其他审评信息")
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
    @SystemLog(description="修改核活动及其他审评信息")
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
    @PostMapping("/geActivityCheckById")
    public ResponseBo getActivityCheckById(@RequestBody String id) {
        return activityCheckService.getActivityCheckById(id);
    }

    /**
     * 删除核活动及其他审评信息信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteActivityCheckById")
    @SystemLog(description="删除核活动及其他审评信息")
    public ResponseBo deleteActivityCheckById(@RequestBody String id) {
        if (id != null) {
            int result = activityCheckService.deleteActivityCheckById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出核活动及其他审评信息
     */
    @RequestMapping(value = "/exportActivityCheck", method = RequestMethod.GET)
    @SystemLog(description = "导出核活动及其他审评信息")
    public void exportActivityCheck(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "facName", required = false) String facName,
            @RequestParam(value = "typeIds", required = false) String typeIds,
            @RequestParam(value = "content", required = false) String content, HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!facName.isEmpty()) {
            list.add(new Condition("facName", facName));
        }
        if (!typeIds.isEmpty()) {
            list.add(new Condition("typeIds", Stream.of(typeIds).collect(toList())));
        }
        if (!content.isEmpty()) {
            list.add(new Condition("content", content));
        }


        Page page = new Page();
        page.setConditions(list);

        activityCheckService.exportActivityCheck(page, response);

    }
}
