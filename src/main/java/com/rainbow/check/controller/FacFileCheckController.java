package com.rainbow.check.controller;


import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.check.service.FacFileCheckService;
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
 * 核设施审评文件信息管理
 */
@RestController
@RequestMapping("facfilecheck")
public class FacFileCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacFileCheckService facFileCheckService;

    /**
     * 添加核设施审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/addFacFileCheck")
    @SystemLog(description="添加核设施审评文件信息")
    public ResponseBo add(@RequestBody FacFileCheck facFileCheck) {
        int result = facFileCheckService.addFacFileCheck(facFileCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyFacFileCheck")
    @SystemLog(description="修改核设施审评文件信息")
    public ResponseBo modify(@RequestBody FacFileCheck facFileCheck) {

        int result = facFileCheckService.updateAll(facFileCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取核设施审评文件信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getFacFileCheckList")
    public ResponseBo getFacFileCheckList(@RequestBody Page page) {

        return facFileCheckService.getFacFileCheckList(page);
    }

    /**
     * 获取核设施审评文件详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getFacFileCheckById")
    public ResponseBo getFacFileCheckById(String id) {
        return facFileCheckService.getFacFileCheckById(id);
    }

    /**
     * 删除核设施审评文件信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteFacFileCheckByIds")
    @SystemLog(description="删除核设施审评文件信息")
    public ResponseBo deleteFacFileCheckByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            facFileCheckService.batchDelete(ids, "id", FacFileCheck.class);
        }
        return ResponseBo.ok();
    }
}
