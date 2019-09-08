package com.rainbow.unit.controller;


import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.domain.ServiceDepart;
import com.rainbow.unit.service.FacService;
import com.rainbow.unit.service.GroupService;
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
    public ResponseBo getFacById(String id) {
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

    @RequestMapping(value = "/exportFac", method = RequestMethod.GET)
    public void exportFac( @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "code", required = false) String code,
                                  @RequestParam(value = "serviceDepart", required = false) String serviceDepart,
                                  @RequestParam(value = "build_start_year", required = false) String build_start_year,
                                  @RequestParam(value = "build_end_year", required = false) String build_end_year,
                                  @RequestParam(value = "supervisionCategoryIds", required = false) String supervisionCategoryIds,
                           @RequestParam(value = "typeIds", required = false) String typeIds,
                           @RequestParam(value = "statusIds", required = false) String statusIds,
                           @RequestParam(value = "reviewStatusIds", required = false) String reviewStatusIds,
                           @RequestParam(value = "permitSituationIds", required = false) String permitSituationIds,
                           @RequestParam(value = "is_earthquake", required = false) String is_earthquake,
                           @RequestParam(value = "is_flood", required = false) String is_flood,
                           HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!code.isEmpty()) {
            list.add(new Condition("code", code));
        }
        if (!serviceDepart.isEmpty()) {
            list.add(new Condition("serviceDepart", serviceDepart));
        }
        if (!build_start_year.isEmpty()) {
            list.add(new Condition("build_start_year", DateUtils.GmtStringToDate(build_start_year)));
        }
        if (!build_end_year.isEmpty()) {
            list.add(new Condition("build_end_year", DateUtils.GmtStringToDate(build_end_year)));
        }
        if (!supervisionCategoryIds.isEmpty()) {

            list.add(new Condition("supervisionCategoryIds", Stream.of(supervisionCategoryIds).collect(toList())));
        }

        if (!typeIds.isEmpty()) {

            list.add(new Condition("typeIds", Stream.of(typeIds).collect(toList())));
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

        if (!is_earthquake.isEmpty()) {
            list.add(new Condition("is_earthquake", is_earthquake));
        }

        if (!is_flood.isEmpty()) {
            list.add(new Condition("is_flood", is_flood));
        }
        Page page = new Page();
        page.setConditions(list);

        facService.exportFac(page, response);

    }
}
