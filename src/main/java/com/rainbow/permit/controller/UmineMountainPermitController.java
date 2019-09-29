package com.rainbow.permit.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.permit.domain.UmineMountainPermit;
import com.rainbow.permit.domain.UminePlacePermit;
import com.rainbow.permit.service.UmineMountainPermitService;
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
 * 铀矿山井下消防许可信息管理
 */
@RestController
@RequestMapping("uminemountainpermit")
public class UmineMountainPermitController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UmineMountainPermitService umineMountainPermitService;

    /**
     * 添加铀矿山井下消防许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUmineMountainPermit")
    @SystemLog(description="添加铀矿山井下消防许可信息")
    public ResponseBo add(@RequestBody UmineMountainPermit umineMountainPermit) {
        int result = umineMountainPermitService.addUmineMountainPermit(umineMountainPermit);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀矿山井下消防许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUmineMountainPermit")
    @SystemLog(description="修改铀矿山井下消防许可信息")
    public ResponseBo modify(@RequestBody UmineMountainPermit umineMountainPermit) {

        int result = umineMountainPermitService.modifyUmineMountainPermit(umineMountainPermit);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀矿山井下消防许可信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getUmineMountainPermitList")
    public ResponseBo getUmineMountainPermitList(@RequestBody Page page) {

        return umineMountainPermitService.getUmineMountainPermitList(page);
    }

    /**
     * 获取铀矿山井下消防许可详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getUmineMountainPermitById")
    public ResponseBo getUmineMountainPermitById(String id) {
        return umineMountainPermitService.getUmineMountainPermitById(id);
    }

    /**
     * 删除铀矿山井下消防许可信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteUmineMountainPermitByIds")
    @SystemLog(description="删除铀矿山井下消防许可信息")
    public ResponseBo deleteUmineMountainPermitByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            umineMountainPermitService.batchDelete(ids, "id", UmineMountainPermit.class);
        }
        return ResponseBo.ok();
    }

    /**
     * 导出日常监督信息
     */
    @RequestMapping(value = "/exportUminemountainPermit", method = RequestMethod.GET)
    @SystemLog(description = "导出铀矿山井下消防许可信息")
    public void exportUminemountainPermit(
            @RequestParam(value = "umineName", required = false) String umineName,
            @RequestParam(value = "umineMountainName", required = false) String umineMountainName,
            @RequestParam(value = "record_start_date", required = false) String record_start_date,
            @RequestParam(value = "record_end_date", required = false) String record_end_date,
            @RequestParam(value = "accept_start_date", required = false) String accept_start_date,
            @RequestParam(value = "accept_end_date", required = false) String accept_end_date,
            HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!umineName.isEmpty()) {
            list.add(new Condition("umineName", umineName));
        }
        if (!umineMountainName.isEmpty()) {
            list.add(new Condition("umineMountainName", umineMountainName));
        }
        if (!record_start_date.isEmpty()) {
            list.add(new Condition("record_start_date", DateUtils.GmtStringToDate(record_start_date)));
        }
        if (!record_end_date.isEmpty()) {
            list.add(new Condition("record_end_date", DateUtils.GmtStringToDate(record_end_date)));
        }

        if (!record_start_date.isEmpty()) {
            list.add(new Condition("accept_start_date", DateUtils.GmtStringToDate(accept_start_date)));
        }
        if (!record_end_date.isEmpty()) {
            list.add(new Condition("accept_end_date", DateUtils.GmtStringToDate(accept_end_date)));
        }

        Page page = new Page();
        page.setConditions(list);

        umineMountainPermitService.exportUminemountainPermit(page, response);

    }
}
