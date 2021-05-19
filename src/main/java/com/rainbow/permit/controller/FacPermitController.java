package com.rainbow.permit.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.domain.PermitPublishScope;
import com.rainbow.permit.service.FacPermitService;
import com.rainbow.permit.service.PermitPublishScopeService;
import com.rainbow.unit.domain.Umine;
import com.rainbow.unit.domain.UminePlaceImprove;
import com.rainbow.unit.service.UmineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.stream.Collectors.toList;

/**
 * Created by 13260 on 2019/5/11. 核设施许可信息管理
 */
@RestController
@RequestMapping("facpermit")
public class FacPermitController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacPermitService facPermitService;

    @Autowired
    PermitPublishScopeService scopeService;

    /**
     * 添加核设施许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/addFacPermit")
    @SystemLog(description = "添加核设施许可信息")
    public ResponseBo add(@RequestBody FacPermit facPermit) {
        int result = facPermitService.addFacPermit(facPermit);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyFacPermit")
    @SystemLog(description = "修改核设施许可信息")
    public ResponseBo modify(@RequestBody FacPermit facPermit) {

        int result = facPermitService.modifyFacPermit(facPermit);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 核设施许可信息审核
     *
     * @param id    核设施许可信息的id
     * @param state 状态，0-草稿，1-提交/待审核，2-审核打回，3-审核通过
     * @return: com.rainbow.common.domain.ResponseBo
     **/
    @PostMapping("/audit")
    @SystemLog(description = "修改核设施许可信息")
    public ResponseBo audit(@RequestBody FacPermit facPermit) {
        int result = facPermitService.audit(facPermit.getId(), facPermit.getState());
        if (result == 1) {
            return ResponseBo.ok("操作成功");
        } else {
            return ResponseBo.error("操作失败");
        }
    }

    /**
     * 获取核设施许可信息列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getFacPermitList")
    public ResponseBo getFacPermitList(@RequestBody Page page) {

        return facPermitService.getFacPermitList(page);
    }

    /**
     * 获取核设施许可信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getFacPermitById")
    public ResponseBo getFacPermitById(String id) {
        return facPermitService.getFacPermitById(id);
    }

    /**
     * 删除核设施许可信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteFacPermitByIds")
    @SystemLog(description = "删除核设施许可信息")
    public ResponseBo deleteFacPermitByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            facPermitService.batchDelete(ids, "id", FacPermit.class);
            scopeService.batchDelete(ids, "permitId", PermitPublishScope.class);
        }
        return ResponseBo.ok();
    }

    /**
     * 导出日常监督信息
     */
    @RequestMapping(value = "/exportFacPermit", method = RequestMethod.GET)
    @SystemLog(description = "导出核设施许可信息")
    public void exportFacPermit(
            @RequestParam(value = "serviceDepartName", required = false) String serviceDepartName,
            @RequestParam(value = "facName", required = false) String facName,
            @RequestParam(value = "permitStageIds", required = false) String permitStageIds,
            @RequestParam(value = "start_date", required = false) String start_date,
            @RequestParam(value = "end_date", required = false) String end_date, HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!serviceDepartName.isEmpty()) {
            list.add(new Condition("serviceDepartName", serviceDepartName));
        }
        if (!facName.isEmpty()) {
            list.add(new Condition("facName", facName));
        }
        if (!permitStageIds.isEmpty()) {
            list.add(new Condition("permitStageIds", Stream.of(permitStageIds).collect(toList())));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }

        Page page = new Page();
        page.setConditions(list);

        facPermitService.exportFacPermit(page, response);

    }

    /**
     * 导入
     *
     * @param request
     * @return
     */
    @SystemLog(description = "导入核设施许可信息")
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo importData(HttpServletRequest request) {

        return facPermitService.importData(request);
    }
}
