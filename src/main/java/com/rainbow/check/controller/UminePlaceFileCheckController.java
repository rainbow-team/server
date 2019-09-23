package com.rainbow.check.controller;


import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.check.domain.UminePlaceFileCheck;
import com.rainbow.check.service.ActivityFileCheckService;
import com.rainbow.check.service.UminePlaceFileCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 铀尾矿(渣)库审评文件信息管理
 */
@RestController
@RequestMapping("umineplacefilecheck")
    public class UminePlaceFileCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UminePlaceFileCheckService uminePlaceFileCheckService;

    /**
     * 添加铀尾矿(渣)库审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUminePlaceFileCheck")
    @SystemLog(description="添加铀尾矿(渣)库审评文件信息")
    public ResponseBo add(@RequestBody UminePlaceFileCheck uminePlaceFileCheck) {
        int result = uminePlaceFileCheckService.addUminePlaceFileCheck(uminePlaceFileCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀尾矿(渣)库审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUminePlaceFileCheck")
    @SystemLog(description="修改铀尾矿(渣)库审评文件信息")
    public ResponseBo modify(@RequestBody UminePlaceFileCheck uminePlaceFileCheck) {

        int result = uminePlaceFileCheckService.updateAll(uminePlaceFileCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取铀尾矿(渣)库审评文件信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getUminePlaceFileCheckList")
    public ResponseBo getUminePlaceFileCheckList(@RequestBody Page page) {

        return uminePlaceFileCheckService.getUminePlaceFileCheckList(page);
    }

    /**
     * 获取铀尾矿(渣)库审评文件详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getUminePlaceFileCheckById")
    public ResponseBo getUmineFileCheckById(String id) {
        return uminePlaceFileCheckService.getUminePlaceFileCheckById(id);
    }

    /**
     * 删除铀尾矿(渣)库审评文件信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteUminePlaceFileCheckByIds")
    @SystemLog(description="删除铀尾矿(渣)库审评文件信息")
    public ResponseBo deleteUminePlaceFileCheckByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            uminePlaceFileCheckService.batchDelete(ids, "id", UminePlaceFileCheck.class);
        }
        return ResponseBo.ok();
    }
}
