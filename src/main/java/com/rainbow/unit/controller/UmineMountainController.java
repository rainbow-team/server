package com.rainbow.unit.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.UmineMountain;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.UmineMountainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    @SystemLog(description="添加铀矿山信息")
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
    @SystemLog(description="修改铀矿山信息")
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
    @SystemLog(description="删除铀矿山信息")
    public ResponseBo deleteUmineMountainById(@RequestBody String id) {
        if (id != null) {
            int result = umineMountainService.deleteUmineMountainById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 根据铀矿冶单位的ID寻找对应所有的铀矿山
     *
     * @param
     * @return
     */
    @PostMapping("/getUminemountainListByUmineId")
    public ResponseBo getUminemountainListByUmineId(@RequestBody String umineId) {
        if (umineId != null) {
            return umineMountainService.getUminemountainListByUmineId(umineId);
        }
        return ResponseBo.ok("获取失败");
    }

    @RequestMapping(value = "/exportUmineMountain", method = RequestMethod.GET)
    @SystemLog(description = "铀矿山信息")
    public void exportUmineMountain(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "umineName", required = false) String umineName,
                          @RequestParam(value = "build_start_year", required = false) String build_start_year,
                          @RequestParam(value = "build_end_year", required = false) String build_end_year,
                          @RequestParam(value = "statusIds", required = false) String statusIds,
                          @RequestParam(value = "recordIds", required = false) String recordIds,
                          @RequestParam(value = "acceptIds", required = false) String acceptIds,
                          HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!umineName.isEmpty()) {
            list.add(new Condition("umineName", umineName));
        }

        if (!build_start_year.isEmpty()) {
            list.add(new Condition("build_start_year", DateUtils.GmtStringToDate(build_start_year)));
        }
        if (!build_end_year.isEmpty()) {
            list.add(new Condition("build_end_year", DateUtils.GmtStringToDate(build_end_year)));
        }

        if (!statusIds.isEmpty()) {

            list.add(new Condition("statusIds", Stream.of(statusIds).collect(toList())));
        }


        if (!recordIds.isEmpty()) {

            list.add(new Condition("recordIds", Stream.of(recordIds).collect(toList())));
        }

        if (!acceptIds.isEmpty()) {

            list.add(new Condition("acceptIds", Stream.of(acceptIds).collect(toList())));
        }

        Page page = new Page();
        page.setConditions(list);

        umineMountainService.exportUmineMountain(page, response);

    }
}
