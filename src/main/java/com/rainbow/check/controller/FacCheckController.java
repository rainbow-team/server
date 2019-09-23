package com.rainbow.check.controller;


import com.rainbow.check.domain.FacCheck;
import com.rainbow.check.service.FacCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.service.ActivityPermitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核设施审评信息管理
 */
@RestController
@RequestMapping("faccheck")
public class FacCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacCheckService facCheckService;

    /**
     * 添加核设施审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/addFacCheck")
    @SystemLog(description="添加核设施审评信息")
    public ResponseBo add(@RequestBody FacCheck facCheck) {
        int result = facCheckService.addFacCheck(facCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyFacCheck")
    @SystemLog(description="修改核设施审评信息")
    public ResponseBo modify(@RequestBody FacCheck facCheck) {

        int result = facCheckService.modifyFacCheck(facCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核设施审评信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getFacCheckList")
    public ResponseBo getFacCheckList(@RequestBody Page page) {

        return facCheckService.getFacCheckList(page);
    }

    /**
     * 获取核设施审评信息详情
     *
     * @param id
     * @return
     */
    @PostMapping("/geFacCheckById")
    public ResponseBo getFacCheckById(@RequestBody  String id) {
        return facCheckService.getFacCheckById(id);
    }

    /**
     * 删除核设施审评信息信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteFacCheckById")
    @SystemLog(description="删除核设施审评信息信息")
    public ResponseBo deleteFacCheckById(@RequestBody String id) {
        if (id != null) {
            int result = facCheckService.deleteFacCheckById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }
}
