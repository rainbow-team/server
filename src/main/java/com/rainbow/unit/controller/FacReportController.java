package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.FacReport;
import com.rainbow.unit.service.FacImproveService;
import com.rainbow.unit.service.FacReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 定期报告管理
 */
@RestController
@RequestMapping("facreport")
public class FacReportController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacReportService facReportService;

    /**
     * 添加定期报告信息
     *
     * @param
     * @return
     */
    @PostMapping("/addFacReport")
    public ResponseBo add(@RequestBody FacReport facReport) {
        int result = facReportService.save(facReport);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改定期报告信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyFacReport")
    public ResponseBo modify(@RequestBody FacReport facReport) {

        int result = facReportService.updateAll(facReport);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取定期报告信息列表
     * @param page
     * @return
     */
    @PostMapping("/getFacReportList")
    public ResponseBo getFacReportList(@RequestBody Page page){

        return facReportService.getFacReportList(page);
    }

    /**
     * 获取定期报告信息详情
     * @param id
     * @return
     */
    @GetMapping("/getFacReportById")
    public ResponseBo getFacReportById(String id){
        FacReport result =  facReportService.getFacReportById(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除定期报告信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteFacReportByIds")
    public ResponseBo deleteFacReportByByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            facReportService.deleteFacReport(ids);
        }
        return ResponseBo.ok();
    }
}
