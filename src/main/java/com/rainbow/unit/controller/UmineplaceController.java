package com.rainbow.unit.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Umineplace;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.UmineplaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 铀尾矿(渣)库信息管理
 */
@RestController
@RequestMapping("umineplace")
public class UmineplaceController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UmineplaceService umineplaceService;

    /**
     * 添加铀尾矿(渣)库信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUmineplace")
    @SystemLog(description="添加铀尾矿(渣)库信息")
    public ResponseBo add(@RequestBody Umineplace umineplace) {
        int result = umineplaceService.addUmineplace(umineplace);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀尾矿(渣)库信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUmineplace")
    @SystemLog(description="修改铀尾矿(渣)库信息")
    public ResponseBo modify(@RequestBody Umineplace umineplace) {

        int result = umineplaceService.modifyUmineplace(umineplace);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀尾矿(渣)库信息列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getUmineplaceList")
    public ResponseBo getUmineplaceList(@RequestBody Page page) {

        return umineplaceService.getUmineplaceList(page);
    }

    /**
     * 获取铀尾矿(渣)库信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getUmineplaceById")
    public ResponseBo getUmineplaceById(String id) {
        return umineplaceService.getUmineplaceById(id);
    }

    /**
     * 删除铀尾矿(渣)库信息信息
     *
     * @param
     * @return
     */
    @PostMapping("/deleteUmineplaceById")
    @SystemLog(description="删除铀尾矿(渣)库信息信息")
    public ResponseBo deleteUmineplaceById(@RequestBody String id) {
        if (id != null) {
            int result = umineplaceService.deleteUmineplaceById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 根据铀矿冶单位的ID寻找对应所有的的铀尾矿(渣)库
     *
     * @param
     * @return
     */
    @PostMapping("/getUmineplaceListByUmineId")
    public ResponseBo getUmineplaceListByUmineId(@RequestBody String umineId) {
        if (umineId != null) {
            return umineplaceService.getUmineplaceListByUmineId(umineId);
        }
        return ResponseBo.ok("获取失败");
    }
}
