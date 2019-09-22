package com.rainbow.supervision.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.supervision.domain.Welder;
import com.rainbow.supervision.service.WelderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 焊接人员资质管理
 */
@RestController
@RequestMapping("welder")
public class WelderController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WelderService supervisionWelderService;

    /**
     * 添加焊接人员资质信息
     *
     * @param
     * @return
     */
    @PostMapping("/addWelder")
    @SystemLog(description="添加焊接人员资质信息")
    public ResponseBo add(@RequestBody Welder supervisionWelder) {
        int result = supervisionWelderService.addWelder(supervisionWelder);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改焊接人员资质信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyWelder")
    @SystemLog(description="修改焊接人员资质信息")
    public ResponseBo modify(@RequestBody Welder supervisionWelder) {

        int result = supervisionWelderService.modifyWelder(supervisionWelder);

        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取焊接人员资质列表
     * @param page
     * @return
     */
    @PostMapping("/getWelderList")
    public ResponseBo getWelderList(@RequestBody Page page){

        return supervisionWelderService.getWelderList(page);
    }

    /**
     * 获取焊接人员资质详情
     * @param id
     * @return
     */
    @GetMapping("/getWelderById")
    public ResponseBo getWelderById(String id) {
        Welder welder = supervisionWelderService.selectByKey(id);
        return ResponseBo.ok(welder);
    }

    /**
     * 删除焊接人员资质信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteWelderByIds")
    @SystemLog(description="删除焊接人员资质信息")
    public ResponseBo deleteWelderByIds(@RequestBody List<String> ids){
        if ((ids != null) && (ids.size() > 0)) {
            supervisionWelderService.batchDelete(ids,"id", Welder.class);
            return ResponseBo.ok("删除成功!");
        }
        return ResponseBo.error("删除失败，请重试!");
    }

    /**
     * 导出焊接人员资质信息
     */
    @RequestMapping(value = "/exportWelder", method = RequestMethod.GET)
    @SystemLog(description="导出焊接人员资质信息")
    public void exportWelder( @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "employ_depart", required = false) String employ_depart,
                                    @RequestParam(value = "exam_project", required = false) String exam_project,
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
        if (!exam_project.isEmpty()) {
            list.add(new Condition("exam_project", exam_project));
        }

        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }


        Page page = new Page();
        page.setConditions(list);

        supervisionWelderService.exportWelderTrain(page,response);

    }
}
