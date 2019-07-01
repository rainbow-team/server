package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.SupervisionExpert;
import com.rainbow.supervision.service.BreakCheckerService;
import com.rainbow.supervision.service.SupervisionExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/addBreakCheckerBreakChecker")
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
    @GetMapping("/getExpertById")
    public ResponseBo getExpertById(String id){
        BreakChecker result =  breakCheckerService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除无损检验人员资质信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteBreakCheckerByIds")
    public ResponseBo deleteBreakCheckerByIds(@RequestBody List<String> ids){
        breakCheckerService.batchDelete(ids,"id",BreakChecker.class);
        return ResponseBo.ok();
    }
}
