package com.rainbow.unit.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.UmineMountainImprove;
import com.rainbow.unit.domain.UminePlaceImprove;
import com.rainbow.unit.service.UmineMountainImproveService;
import com.rainbow.unit.service.UminePlaceImproveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 铀矿山安技改信息管理
 */
@RestController
@RequestMapping("uminemontainimprove")
public class UmineMoutainImproveController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UmineMountainImproveService umineMountainImproveService;

    /**
     * 添加铀矿山安技改信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUmineMountainImprove")
    @SystemLog(description="添加铀矿山安技改信息")
    public ResponseBo add(@RequestBody UmineMountainImprove umineMountainImprove) {
        int result = umineMountainImproveService.addUmineMountainImprove(umineMountainImprove);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀矿山安技改信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUmineMountainImprove")
    @SystemLog(description="修改铀矿山安技改信息")
    public ResponseBo modify(@RequestBody UmineMountainImprove umineMountainImprove) {

        int result = umineMountainImproveService.updateAll(umineMountainImprove);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀矿山安技改列表
     * @param page
     * @return
     */
    @PostMapping("/getUmineMountainImproveList")
    public ResponseBo getFacImproveList(@RequestBody Page page){

        return umineMountainImproveService.getUmineMountainImproveList(page);
    }

    /**
     * 获取铀矿山安技改详情
     * @param id
     * @return
     */
    @GetMapping("/getUmineMountainImproveById")
    public ResponseBo getUmineMountainImproveById(String id){
        UmineMountainImprove result =  umineMountainImproveService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除铀矿山安技改信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteUmineMountainImproveByIds")
    @SystemLog(description="删除铀矿山安技改信息")
    public ResponseBo deleteUmineMountainImproveByByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            umineMountainImproveService.batchDelete(ids,"id",UmineMountainImprove.class);
        }
        return ResponseBo.ok();
    }
}
