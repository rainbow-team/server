package com.rainbow.unit.controller;


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
     * @param ids
     * @return
     */
    @PostMapping("/deleteUmineplaceByIds")
    public ResponseBo deleteUmineplaceByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            umineplaceService.deleteUmineplaceByIds(ids);
        }
        return ResponseBo.ok();
    }
}
