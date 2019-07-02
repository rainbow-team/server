package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.domain.Umine;
import com.rainbow.unit.service.GroupService;
import com.rainbow.unit.service.UmineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 铀矿冶单位管理
 */
@RestController
@RequestMapping("umine")
public class UmineController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UmineService umineService;

    /**
     * 添加铀矿冶单位信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUmine")
    public ResponseBo add(@RequestBody Umine umine) {
        int result = umineService.addUmine(umine);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀矿冶单位信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUmine")
    public ResponseBo modify(@RequestBody Umine umine) {

        int result = umineService.modifyUmine(umine);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀矿冶单位信息列表
     * @param page
     * @return
     */
    @PostMapping("/getUmineList")
    public ResponseBo getUmineList(@RequestBody Page page){

        return umineService.getUmineList(page);
    }

    /**
     * 获取铀矿冶单位信息详情
     * @param id
     * @return
     */
    @GetMapping("/getUmineById")
    public ResponseBo getUmineById(String id){
        Umine result =  umineService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除铀矿冶单位信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteUmineByIds")
    public ResponseBo deleteUmineByIds(@RequestBody List<String> ids){
        umineService.batchDelete(ids,"id",Umine.class);
        return ResponseBo.ok();
    }
}
