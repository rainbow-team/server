package com.rainbow.unit.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Umineplace;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.UmineplaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.stream.Collectors.toList;

/**
 * Created by 13260 on 2019/5/11. 铀尾矿(渣)库信息管理
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
    @SystemLog(description = "添加铀尾矿(渣)库信息")
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
    @SystemLog(description = "修改铀尾矿(渣)库信息")
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
    @SystemLog(description = "删除铀尾矿(渣)库信息")
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

    /**
     * 导入
     * 
     * @param request
     * @return
     */
    @SystemLog(description = "导入铀尾矿(渣)库信息")
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo importData(HttpServletRequest request) {

        return umineplaceService.importData(request);
    }

    @RequestMapping(value = "/exportUminePlace", method = RequestMethod.GET)
    @SystemLog(description = "铀尾矿（渣）库信息")
    public void exportFac(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "umineName", required = false) String umineName,
                          @RequestParam(value = "build_start_year", required = false) String build_start_year,
                          @RequestParam(value = "build_end_year", required = false) String build_end_year,
                          @RequestParam(value = "levelIds", required = false) String levelIds,
                          @RequestParam(value = "statusIds", required = false) String statusIds,
                          @RequestParam(value = "reviewStatusIds", required = false) String reviewStatusIds,
                          @RequestParam(value = "permitSituationIds", required = false) String permitSituationIds,
                          @RequestParam(value = "have_monitor", required = false) String have_monitor,
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
        if (!levelIds.isEmpty()) {

            list.add(new Condition("levelIds", Stream.of(levelIds).collect(toList())));
        }

        if (!statusIds.isEmpty()) {

            list.add(new Condition("statusIds", Stream.of(statusIds).collect(toList())));
        }


        if (!reviewStatusIds.isEmpty()) {

            list.add(new Condition("reviewStatusIds", Stream.of(reviewStatusIds).collect(toList())));
        }

        if (!permitSituationIds.isEmpty()) {

            list.add(new Condition("permitSituationIds", Stream.of(permitSituationIds).collect(toList())));
        }

        if (!have_monitor.isEmpty()) {
            list.add(new Condition("have_monitor", have_monitor));
        }

        Page page = new Page();
        page.setConditions(list);

        umineplaceService.exportUminePlace(page, response);

    }
}
