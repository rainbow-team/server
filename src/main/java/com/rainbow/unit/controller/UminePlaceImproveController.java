package com.rainbow.unit.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.UmineMountainImprove;
import com.rainbow.unit.domain.UminePlaceImprove;
import com.rainbow.unit.service.FacImproveService;
import com.rainbow.unit.service.UminePlaceImproveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 铀尾矿(渣)安技改信息管理
 */
@RestController
@RequestMapping("umineplaceimprove")
public class UminePlaceImproveController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UminePlaceImproveService uminePlaceImproveService;

    /**
     * 添加铀尾矿(渣)安技改信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUminePlaceImprove")
    @SystemLog(description="添加铀尾矿(渣)安技改信息")
    public ResponseBo add(@RequestBody UminePlaceImprove uminePlaceImprove) {
        int result = uminePlaceImproveService.addPlaceImprove(uminePlaceImprove);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀尾矿(渣)安技改信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUminePlaceImprove")
    @SystemLog(description="修改铀尾矿(渣)安技改信息")
    public ResponseBo modify(@RequestBody UminePlaceImprove uminePlaceImprove) {

        int result = uminePlaceImproveService.updateAll(uminePlaceImprove);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀尾矿(渣)安技改列表
     * @param page
     * @return
     */
    @PostMapping("/getUminePlaceImproveList")
    public ResponseBo getUminePlaceImproveList(@RequestBody Page page){

        return uminePlaceImproveService.getUminePlaceImproveList(page);
    }

    /**
     * 获取铀尾矿(渣)安技改详情
     * @param id
     * @return
     */
    @GetMapping("/getUminePlaceImproveById")
    public ResponseBo getUminePlaceImproveById(String id){
        UminePlaceImprove result =  uminePlaceImproveService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除铀尾矿(渣)安技改信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteUminePlaceImproveByIds")
    @SystemLog(description="删除铀尾矿(渣)安技改信息")
    public ResponseBo deleteUminePlaceImproveByByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            uminePlaceImproveService.batchDelete(ids,"id",UminePlaceImprove.class);
        }
        return ResponseBo.ok();
    }
}
