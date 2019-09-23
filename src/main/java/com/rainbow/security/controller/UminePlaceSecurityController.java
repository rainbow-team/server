package com.rainbow.security.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.service.FacSecurityService;
import com.rainbow.security.service.UminePlaceSecurityService;
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
 * 铀尾矿(渣)库安全问题管理
 */
@RestController
@RequestMapping("umineplacesecurity")
public class UminePlaceSecurityController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UminePlaceSecurityService uminePlaceSecurityService;

    /**
     * 添加铀尾矿(渣)库安全问题
     *
     * @param
     * @return
     */
    @PostMapping("/addUminePlaceSecurity")
    @SystemLog(description="添加铀尾矿(渣)库安全问题")
    public ResponseBo add(@RequestBody UminePlaceSecurity uminePlaceSecurity) {
        int result = uminePlaceSecurityService.addUminePlaceSecurity(uminePlaceSecurity);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀尾矿(渣)库安全问题
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUminePlaceSecurity")
    @SystemLog(description="修改铀尾矿(渣)库安全问题")
    public ResponseBo modify(@RequestBody UminePlaceSecurity uminePlaceSecurity) {

        int result = uminePlaceSecurityService.modifyUminePlaceSecurity(uminePlaceSecurity);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀尾矿(渣)库安全问题
     *
     * @param page
     * @return
     */
    @PostMapping("/getUminePlaceSecurityList")
    public ResponseBo getUminePlaceSecurityList(@RequestBody Page page) {

        return uminePlaceSecurityService.getUminePlaceSecurityList(page);
    }

    /**
     * 获取铀尾矿(渣)库安全问题详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geUminePlaceSecurityById")
    public ResponseBo getUminePlaceSecurityById(String id) {
        return uminePlaceSecurityService.getUminePlaceSecurityById(id);
    }

    /**
     * 删除铀尾矿(渣)库安全问题信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteUminePlaceSecurityById")
    @SystemLog(description="删除铀尾矿(渣)库安全问题信息")
    public ResponseBo deleteUminePlaceSecurityByIds(@RequestBody String id) {
        if (id != null) {
            int result = uminePlaceSecurityService.deleteByKey(id);
            return result == 0 ? ResponseBo.error("删除失败!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出核设施安全问题
     */
    @RequestMapping(value = "/exportUmineplaceSecurity", method = RequestMethod.GET)
    @SystemLog(description="导出核设施安全问题")
    public void exportUmineplaceSecurity( @RequestParam(value = "umineName", required = false) String umineName,
                                  @RequestParam(value = "uminePlaceName", required = false) String uminePlaceName,
                                  @RequestParam(value = "statusTypeIds", required = false) String statusTypeIds,
                                  @RequestParam(value = "checkTypeIds", required = false) String checkTypeIds,
                                  @RequestParam(value = "content", required = false) String content,
                                  @RequestParam(value = "start_date", required = false) String start_date,
                                  @RequestParam(value = "end_date", required = false) String end_date,
                                  @RequestParam(value = "questionTypeIds", required = false) String questionTypeIds,
                                  @RequestParam(value = "questionNatureIds", required = false) String questionNatureIds,
                                  @RequestParam(value = "reformStatusTypeIds", required = false) String reformStatusTypeIds,
                                  HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!umineName.isEmpty()) {
            list.add(new Condition("umineName", umineName));
        }
        if (!uminePlaceName.isEmpty()) {
            list.add(new Condition("uminePlaceName", uminePlaceName));
        }
        if (!statusTypeIds.isEmpty()) {
            list.add(new Condition("statusTypeIds",  Stream.of(statusTypeIds).collect(toList())));
        }
        if (!checkTypeIds.isEmpty()) {
            list.add(new Condition("checkLevelIds",  Stream.of(checkTypeIds).collect(toList())));
        }
        if (!content.isEmpty()) {
            list.add(new Condition("content", content));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }
        if (!questionTypeIds.isEmpty()) {
            list.add(new Condition("questionTypeIds",  Stream.of(questionTypeIds).collect(toList())));
        }
        if (!questionNatureIds.isEmpty()) {
            list.add(new Condition("questionNatureIds",  Stream.of(questionNatureIds).collect(toList())));
        }
        if (!reformStatusTypeIds.isEmpty()) {
            list.add(new Condition("reformStatusTypeIds",  Stream.of(reformStatusTypeIds).collect(toList())));
        }

        Page page = new Page();
        page.setConditions(list);

        uminePlaceSecurityService.exportUmineplaceSecurity(page,response);

    }
}
