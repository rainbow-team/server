package com.rainbow.permit.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.UminePlacePermit;
import com.rainbow.permit.service.ActivityPermitService;
import com.rainbow.permit.service.UminePlacePermitService;
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
 * 铀尾矿(渣)库许可信息管理
 */
@RestController
@RequestMapping("umineplacepermit")
public class UminePlacePermitController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UminePlacePermitService uminePlacePermitService;

    /**
     * 添加铀尾矿(渣)库许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUminePlacePermit")
    @SystemLog(description="添加铀尾矿(渣)库许可信息")
    public ResponseBo add(@RequestBody UminePlacePermit uminePlacePermit) {
        int result = uminePlacePermitService.addUminePlacePermit(uminePlacePermit);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀尾矿(渣)库许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUminePlacePermit")
    @SystemLog(description="修改铀尾矿(渣)库许可信息")
    public ResponseBo modify(@RequestBody UminePlacePermit uminePlacePermit) {

        int result = uminePlacePermitService.modifyUminePlacePermit(uminePlacePermit);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀尾矿(渣)库许可信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getUminePlacePermitList")
    public ResponseBo getUminePlacePermitList(@RequestBody Page page) {

        return uminePlacePermitService.getUminePlacePermitList(page);
    }

    /**
     * 获取铀尾矿(渣)库许可信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getUminePlacePermitById")
    public ResponseBo getUminePlacePermitById(String id) {
        return uminePlacePermitService.getUminePlacePermitById(id);
    }

    /**
     * 删除铀尾矿(渣)库许可信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteUminePlacePermitByIds")
    @SystemLog(description="删除铀尾矿(渣)库许可信息")
    public ResponseBo deleteUminePlacePermitByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            uminePlacePermitService.batchDelete(ids, "id", UminePlacePermit.class);
        }
        return ResponseBo.ok();
    }

    /**
     * 导出日常监督信息
     */
    @RequestMapping(value = "/exportUmineplacePermit", method = RequestMethod.GET)
    @SystemLog(description = "导出铀尾矿(渣)库许可信息")
    public void exportUmineplacePermit(
            @RequestParam(value = "umineName", required = false) String umineName,
            @RequestParam(value = "uminePlaceName", required = false) String uminePlaceName,
            @RequestParam(value = "stageIds", required = false) String stageIds,
            @RequestParam(value = "start_date", required = false) String start_date,
            @RequestParam(value = "end_date", required = false) String end_date, HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!umineName.isEmpty()) {
            list.add(new Condition("umineName", umineName));
        }
        if (!uminePlaceName.isEmpty()) {
            list.add(new Condition("uminePlaceName", uminePlaceName));
        }
        if (!stageIds.isEmpty()) {
            list.add(new Condition("stageIds", Stream.of(stageIds).collect(toList())));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }

        Page page = new Page();
        page.setConditions(list);

        uminePlacePermitService.exportUmineplacePermit(page, response);

    }
}
