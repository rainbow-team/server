package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.service.FacService;
import com.rainbow.unit.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核设施信息管理
 */
@RestController
@RequestMapping("fac")
public class FacController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacService facService;

    /**
     * 添加核设施信息
     *
     * @param
     * @return
     */
    @PostMapping("/addFac")
    public ResponseBo add(@RequestBody Fac fac) {
        int result = facService.addFac(fac);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyGroup")
    public ResponseBo modify(@RequestBody Fac fac) {

        int result = facService.modifyFac(fac);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核设施信息列表
     * @param page
     * @return
     */
    @PostMapping("/getFacList")
    public ResponseBo getFacList(@RequestBody Page page){

        return facService.getFacList(page);
    }

    /**
     * 获取核设施信息详情
     * @param id
     * @return
     */
    @GetMapping("/getFacById")
    public ResponseBo getFacById(String id) {
        Fac result = facService.getFacById(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除核设施信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteFacByIds")
    public ResponseBo deleteFacByIds(@RequestBody List<String> ids){
        if((ids!=null)&&(ids.size()>0)) {
            facService.deleteFacByIds(ids);
        }
        return ResponseBo.ok();
    }
}
