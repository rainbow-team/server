package com.rainbow.supervision.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.OperatorLisence;
import com.rainbow.supervision.service.BreakCheckerService;
import com.rainbow.supervision.service.OperatorLisenceService;
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
 * 研究堆操纵员执照管理
 */
@RestController
@RequestMapping("operatorlisence")
public class OperatorLisenceController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OperatorLisenceService operatorLisenceService;

    /**
     * 添加研究堆操作员执照信息
     *
     * @param
     * @return
     */
    @PostMapping("/addOperatorLisence")
    @SystemLog(description="添加研究堆操作员执照信息")
    public ResponseBo add(@RequestBody OperatorLisence operatorLisence) {
        int result = operatorLisenceService.addOperatorLisence(operatorLisence);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改研究堆操作员执照信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyOperatorLisence")
    @SystemLog(description="修改研究堆操作员执照信息")
    public ResponseBo modify(@RequestBody OperatorLisence operatorLisence) {

        int result = operatorLisenceService.modifyOperatorLisence(operatorLisence);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取研究堆操作员执照信息列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getOperatorLisenceList")
    public ResponseBo getOperatorLisenceList(@RequestBody Page page) {

        return operatorLisenceService.getOperatorLisenceList(page);
    }

    /**
     * 获取研究堆操纵员执照详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getOperatorLisenceById")
    public ResponseBo getOperatorLisenceById(String id) {
        return operatorLisenceService.getOperatorLisenceById(id);
    }

    /**
     * 删除研究堆操作员执照信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteOperatorLisenceByIds")
    @SystemLog(description="删除研究堆操作员执照信息")
    public ResponseBo deleteOperatorLisenceByIds(@RequestBody List<String> ids) {
        int result = operatorLisenceService.batchDelete(ids, "id", OperatorLisence.class);
        return result == 0 ? ResponseBo.error("删除失败") : ResponseBo.ok("删除成功");
    }

    /**
     * 研究堆操纵员执照信息
     */
    @RequestMapping(value = "/exportOperator", method = RequestMethod.GET)
    @SystemLog(description="研究堆操纵员执照信息")
    public void exportOperator( @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "employ_depart", required = false) String employ_depart,
                                  @RequestParam(value = "heap_name", required = false) String heap_name,
                                  @RequestParam(value = "lisenceTypeIds", required = false) String lisenceTypeIds,
                                  @RequestParam(value = "start_date", required = false) String start_date,
                                  @RequestParam(value = "end_date", required = false) String end_date,
                                  HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!employ_depart.isEmpty()) {
            list.add(new Condition("employ_depart", employ_depart));
        }
        if (!heap_name.isEmpty()) {
            list.add(new Condition("heap_name", heap_name));
        }
        if (!lisenceTypeIds.isEmpty()) {
            list.add(new Condition("lisenceTypeIds",  Stream.of(lisenceTypeIds).collect(toList())));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }

        Page page = new Page();
        page.setConditions(list);

        operatorLisenceService.exportOperator(page,response);

    }
}
