package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.service.BreakCheckerService;
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
 * 无损检验人员资质信息管理
 */
@RestController
@RequestMapping("breakchecker")
public class BreakCheckerController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BreakCheckerService breakCheckerService;

    /**
     * 添加无损检验人员资质信息
     *
     * @param
     * @return
     */
    @PostMapping("/addBreakChecker")
    public ResponseBo add(@RequestBody BreakChecker breakChecker) {
        int result = breakCheckerService.addBreakChecker(breakChecker);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改无损检验人员资质信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyBreakChecker")
    public ResponseBo modify(@RequestBody BreakChecker breakChecker) {

        int result = breakCheckerService.modifyBreakChecker(breakChecker);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取无损检验人员资质信息列表
     * @param page
     * @return
     */
    @PostMapping("/getBreakCheckerList")
    public ResponseBo getBreakCheckerList(@RequestBody Page page){

        return breakCheckerService.getBreakCheckerList(page);
    }

    /**
     * 获取无损检验人员资质详情
     * @param id
     * @return
     */
    @GetMapping("/getBreakCheckertById")
    public ResponseBo getBreakCheckerById(String id) {
        return breakCheckerService.getBreakCheckerById(id);
    }

    /**
     * 删除无损检验人员资质信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteBreakCheckerByIds")
    public ResponseBo deleteBreakCheckerByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            breakCheckerService.batchDelete(ids, "id", BreakChecker.class);
            return ResponseBo.ok("删除成功!");
        }

        return ResponseBo.error("删除失败，请重试!");
    }

    /**
     * 导出无损检验人员资质信息
     */
    @RequestMapping(value = "/exportBreakChecker", method = RequestMethod.GET)
    public void exportBreakChecker( @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "employ_depart", required = false) String employ_depart,
                                  @RequestParam(value = "checkMethodIds", required = false) String checkMethodIds,
                                  @RequestParam(value = "checkLevelIds", required = false) String checkLevelIds,
                                  @RequestParam(value = "start_date", required = false) String start_date,
                                  @RequestParam(value = "end_date", required = false) String end_date,
                                  @RequestParam(value = "cert_number", required = false) String cert_number,
                                  HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!employ_depart.isEmpty()) {
            list.add(new Condition("employ_depart", employ_depart));
        }
        if (!checkMethodIds.isEmpty()) {
            list.add(new Condition("checkMethodIds",  Stream.of(checkMethodIds).collect(toList())));
        }
        if (!checkLevelIds.isEmpty()) {
            list.add(new Condition("checkLevelIds",  Stream.of(checkLevelIds).collect(toList())));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }
        if (!cert_number.isEmpty()) {
            list.add(new Condition("cert_number", cert_number));
        }

        Page page = new Page();
        page.setConditions(list);

        breakCheckerService.exportBreakChecker(page,response);

    }
}
