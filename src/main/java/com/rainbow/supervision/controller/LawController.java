package com.rainbow.supervision.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.supervision.domain.SupervisionLaw;
import com.rainbow.supervision.service.LawSupervisionService;
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
 * 监管法规管理
 */
@RestController
@RequestMapping("law")
public class LawController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LawSupervisionService supervisionLawService;

    /**
     * 保存监管法规信息
     *
     * @param
     * @return
     */
    @PostMapping("/addLaw")
    @SystemLog(description="保存监管法规信息")
    public ResponseBo add(@RequestBody SupervisionLaw supervisionLaw) {
        int result = supervisionLawService.addLaw(supervisionLaw);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改监管法规信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyLaw")
    @SystemLog(description="修改监管法规信息")
    public ResponseBo modify(@RequestBody SupervisionLaw supervisionLaw) {

        int result = supervisionLawService.modifyLaw(supervisionLaw);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取监管法规列表
     * @param page
     * @return
     */
    @PostMapping("/getLawList")
    public ResponseBo getLawList(@RequestBody Page page){

        return supervisionLawService.getLawList(page);
    }

    /**
     * 获取法规详情
     * @param id
     * @return
     */
    @GetMapping("/getLawById")
    public ResponseBo getLawById(String id){
        SupervisionLaw result =  supervisionLawService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除法规信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteLawByIds")
    @SystemLog(description="删除法规信息")
    public ResponseBo deleteLawByIds(@RequestBody List<String> ids){
        supervisionLawService.batchDelete(ids,"id",SupervisionLaw.class);
        return ResponseBo.ok();
    }

    /**
     * 导出授权监管机构信息
     */
    @RequestMapping(value = "/exportLaw", method = RequestMethod.GET)
    @SystemLog(description="导出授权监管机构信息")
    public void exportLaw( @RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!code.isEmpty()) {
            list.add(new Condition("code", code));
        }

        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }

        if (!startTime.isEmpty()) {
            list.add(new Condition("startTime", DateUtils.GmtStringToDate(startTime)));
        }
        if (!endTime.isEmpty()) {
            list.add(new Condition("endTime", DateUtils.GmtStringToDate(endTime)));
        }


        Page page = new Page();
        page.setConditions(list);

        supervisionLawService.exportLaw(page,response);

    }
}
