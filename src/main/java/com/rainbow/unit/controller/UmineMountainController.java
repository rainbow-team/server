package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.UmineMountain;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.UmineMountainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 铀矿山管理
 */
@RestController
@RequestMapping("uminemountain")
public class UmineMountainController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UmineMountainService umineMountainService;

    /**
     * 添加铀矿山信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUmineMountain")
    public ResponseBo add(@RequestBody UmineMountain umineMountain) {
        int result = umineMountainService.addUmineMountain(umineMountain);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀矿山信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUmineMountain")
    public ResponseBo modify(@RequestBody UmineMountain umineMountain) {

        int result = umineMountainService.modifyUmineMountain(umineMountain);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀矿山信息列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getUmineMountainList")
    public ResponseBo getUmineMountainList(@RequestBody Page page) {

        return umineMountainService.getUmineMountainList(page);
    }

    /**
     * 获取铀矿山信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getUmineMountainById")
    public ResponseBo getUmineMountainById(String id) {
        return umineMountainService.getUmineMountainById(id);
    }

    /**
     * 删除铀矿山信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteUmineMountainById")
    public ResponseBo deleteUmineMountainById(@RequestBody String id) {
        if (id != null) {
            int result = umineMountainService.deleteUmineMountainById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }
}
