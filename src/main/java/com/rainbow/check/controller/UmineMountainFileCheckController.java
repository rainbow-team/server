package com.rainbow.check.controller;


import com.rainbow.check.domain.UmineMountainFileCheck;
import com.rainbow.check.domain.UmineMountainFileCheck;
import com.rainbow.check.domain.UminePlaceFileCheck;
import com.rainbow.check.service.UmineMountainFileCheckService;
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
 * 铀矿山井下消防审评文件信息管理
 */
@RestController
@RequestMapping("uminemountainfilecheck")
    public class UmineMountainFileCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UmineMountainFileCheckService umineMountainFileCheckService;

    /**
     * 添加铀矿山井下消防审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUmineMountainFileCheck")
    @SystemLog(description="添加铀矿山井下消防审评文件信息")
    public ResponseBo add(@RequestBody UmineMountainFileCheck umineMountainFileCheck) {
        int result = umineMountainFileCheckService.addUmineMountainFileCheck(umineMountainFileCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀矿山井下消防审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUmineMountainFileCheck")
    @SystemLog(description="修改铀矿山井下消防审评文件信息")
    public ResponseBo modify(@RequestBody UmineMountainFileCheck umineMountainFileCheck) {

        int result = umineMountainFileCheckService.updateAll(umineMountainFileCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取铀矿山井下消防审评文件信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getUmineMountainFileCheckList")
    public ResponseBo getUmineMountainFileCheckList(@RequestBody Page page) {

        return umineMountainFileCheckService.getUmineMountainFileCheckList(page);
    }

    /**
     * 获取铀矿山井下消防审评文件详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getUmineMountainFileCheckById")
    public ResponseBo getUmineFileCheckById(String id) {
        return umineMountainFileCheckService.getUmineMountainFileCheckById(id);
    }

    /**
     * 删除铀矿山井下消防审评文件信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteUmineMountainFileCheckByIds")
    @SystemLog(description="删除铀矿山井下消防审评文件信息")
    public ResponseBo deleteUmineMountainFileCheckByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            umineMountainFileCheckService.batchDelete(ids, "id", UmineMountainFileCheck.class);
        }
        return ResponseBo.ok();
    }
}
