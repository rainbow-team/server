package com.rainbow.permit.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.EquipPermit;
import com.rainbow.permit.service.ActivityPermitService;
import com.rainbow.permit.service.EquipPermitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核活动许可信息管理
 */
@RestController
@RequestMapping("activitypermit")
public class ActivityPermitController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ActivityPermitService activityPermitService;

    /**
     * 添加核活动许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/addActivityPermit")
    @SystemLog(description="添加核活动许可信息")
    public ResponseBo add(@RequestBody ActivityPermit activityPermit) {
        int result = activityPermitService.addActivityPermit(activityPermit);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核活动许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyActivityPermit")
    @SystemLog(description="修改核活动许可信息")
    public ResponseBo modify(@RequestBody ActivityPermit activityPermit) {

        int result = activityPermitService.modifyActivityPermit(activityPermit);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核活动许可信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getActivityPermitList")
    public ResponseBo getActivityPermitList(@RequestBody Page page) {

        return activityPermitService.getActivityPermitList(page);
    }

    /**
     * 获取活动许可信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getActivityPermitById")
    public ResponseBo getActivityPermitById(String id) {
        return activityPermitService.getActivityPermitById(id);
    }

    /**
     * 删除核活动许可信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteActivityPermitByIds")
    @SystemLog(description="删除核活动许可信息")
    public ResponseBo deleteActivityPermitByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            activityPermitService.batchDelete(ids, "id", ActivityPermit.class);
        }
        return ResponseBo.ok();
    }
}
