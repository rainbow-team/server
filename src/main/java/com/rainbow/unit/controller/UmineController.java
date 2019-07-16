package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.domain.Umine;
import com.rainbow.unit.domain.UmineExtend;
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
        return umineService.getUmineById(id);
    }

    /**
     * 删除铀矿冶单位信息
     * @param id
     * @return
     */
    @PostMapping("/deleteUmineById")
    public ResponseBo deleteUmineByIds(String id){
        if (id != null) {
            int result = umineService.deleteUmineById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     *获取所有铀矿冶单位信息
     * @param
     * @return
     */
    @PostMapping("/getAllUmine")
    public ResponseBo getAllUmine(){
        List<Umine> result = umineService.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
