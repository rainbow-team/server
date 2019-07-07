package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.ServiceAnnualReport;
import com.rainbow.unit.service.FacService;
import com.rainbow.unit.service.ServiceAnnualReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核设施营运单位年度安全报告管理
 */
@RestController
@RequestMapping("serviceannualreport")
public class ServiceAnnualReportController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ServiceAnnualReportService annualReportService;

    /**
     * 添加核设施营运单位年度安全报告
     *
     * @param
     * @return
     */
    @PostMapping("/addServiceAnnualReport")
    public ResponseBo add(@RequestBody ServiceAnnualReport serviceAnnualReport) {
        int result = annualReportService.addServiceAnnualReport(serviceAnnualReport);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }



    /**
     * 获取核设施信息列表
     * @param page
     * @return
     */
    @PostMapping("/getFacList")
    public ResponseBo getFacList(@RequestBody Page page){

        return annualReportService.getServiceAnnualReportList(page);
    }

/*    *//**
     * 获取核设施信息详情
     * @param id
     * @return
     *//*
    @GetMapping("/getFacById")
    public ResponseBo getFacById(String id){
        Fac result =  facService.selectByKey(id);
        return ResponseBo.ok(result);
    }*/

    /**
     * 删除核设施营运单位年度安全报告
     * @param ids
     * @return
     */
    @PostMapping("/deleteServiceAnnualReportByIds")
    public ResponseBo deleteServiceAnnualReportByIds(@RequestBody List<String> ids){
        annualReportService.batchDelete(ids,"id",ServiceAnnualReport.class);
        return ResponseBo.ok();
    }
}
