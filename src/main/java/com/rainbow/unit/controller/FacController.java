package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.domain.ServiceDepart;
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
    @PostMapping("/modifyFac")
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
     *
     * @param page
     * @return
     */
    @PostMapping("/getFacList")
    public ResponseBo getFacList(@RequestBody Page page) {

        return facService.getFacList(page);
    }

    /**
     * 获取核设施信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getFacById")
    public ResponseBo getFacById(@RequestBody String id) {
        return facService.getFacById(id);
    }

    /**
     * 删除核设施信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteFacById")
    public ResponseBo deleteFacByIds(@RequestBody String id) {
        if (id != null) {
            int result = facService.deleteFacById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }


    /**
     * 根据核设施运营单位的ID获取对应的核设施信息表
     *
     * @param
     * @return
     */
    @PostMapping("/getFacListByServiceid")
    public ResponseBo getFacListByServiceidList(@RequestBody String serviceId) {
        if (serviceId != null) {
            return facService.getFacListByServiceId(serviceId);
        }
        return ResponseBo.ok("获取失败");
    }
}
