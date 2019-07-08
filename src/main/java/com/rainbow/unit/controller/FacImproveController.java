package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.service.FacImproveService;
import com.rainbow.unit.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核设施安技改信息管理
 */
@RestController
@RequestMapping("facimprove")
public class FacImproveController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacImproveService facImproveService;

    /**
     * 添加核设施安技改信息
     *
     * @param
     * @return
     */
    @PostMapping("/addFacImprove")
    public ResponseBo add(@RequestBody FacImprove facImprove) {
        int result = facImproveService.addFacImprove(facImprove);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施安技改信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyFacImprove")
    public ResponseBo modify(@RequestBody FacImprove facImprove) {

        int result = facImproveService.updateAll(facImprove);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核设施安技改信息列表
     * @param page
     * @return
     */
    @PostMapping("/getFacImproveList")
    public ResponseBo getFacImproveList(@RequestBody Page page){

        return facImproveService.getFacImproveList(page);
    }

    /**
     * 获取核设施安技改信息详情
     * @param id
     * @return
     */
    @GetMapping("/getFacImproveById")
    public ResponseBo getFacImproveById(String id){
        FacImprove result =  facImproveService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除核设施安技改信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteFacImproveByIds")
    public ResponseBo deleteImproveByByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            facImproveService.batchDelete(ids,"id",FacImprove.class);
        }
        return ResponseBo.ok();
    }
}
