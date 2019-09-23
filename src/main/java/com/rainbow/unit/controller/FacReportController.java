package com.rainbow.unit.controller;


import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.annotation.SystemLog;
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
 * 核设施定期报告管理
 */
@RestController
@RequestMapping("facreport")
public class FacReportController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacReportService facReportService;

    @Autowired
    FileInfoService fileInfoService;
    /**
     * 添加核设施定期报告信息
     *
     * @param
     * @return
     */
    @PostMapping("/addFacReport")
    @SystemLog(description="添加核设施定期报告信息")
    public ResponseBo add(@RequestBody FacReport facReport) {
        int result = facReportService.addFacReport(facReport);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施定期报告信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyFacReport")
    @SystemLog(description="修改核设施定期报告信息")
    public ResponseBo modify(@RequestBody FacReport facReport) {

        int result = facReportService.updateAll(facReport);
        fileInfoService.updateFileInfoByIds(facReport.getAttachmentList(),facReport.getId());
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核设施定期报告信息列表
     * @param page
     * @return
     */
    @PostMapping("/getFacReportList")
    public ResponseBo getFacReportList(@RequestBody Page page){

        return facReportService.getFacReportList(page);
    }

    /**
     * 获取核设施定期报告信息详情
     * @param id
     * @return
     */
    @GetMapping("/getFacReportById")
    public ResponseBo getFacReportById(String id){
        return facReportService.getFacReportById(id);
    }

    /**
     * 删除核设施定期报告信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteFacReportByIds")
    @SystemLog(description="删除核设施定期报告信息")
    public ResponseBo deleteFacReportByByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            facReportService.batchDelete(ids,"id",FacReport.class);
        }
        return ResponseBo.ok();
    }
}
