package com.rainbow.security.controller;


import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.service.AccidentSecurityService;
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
 * 事故事件管理
 */
@RestController
@RequestMapping("accidentsecurity")
public class AccidentSecurityController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccidentSecurityService accidentSecurityService;

    /**
     * 添加事故事件
     *
     * @param
     * @return
     */
    @PostMapping("/addAccidentSecurity")
    public ResponseBo add(@RequestBody AccidentSecurity accidentSecurity) {
        int result = accidentSecurityService.addAccidentSecurity(accidentSecurity);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改事故事件
     *
     * @param
     * @return
     */
    @PostMapping("/modifyAccidentSecurity")
    public ResponseBo modify(@RequestBody AccidentSecurity accidentSecurity) {

        int result = accidentSecurityService.modifyAccidentSecurity(accidentSecurity);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取事故事件
     *
     * @param page
     * @return
     */
    @PostMapping("/getAccidentSecurityList")
    public ResponseBo getAccidentSecurityList(@RequestBody Page page) {

        return accidentSecurityService.getAccidentSecurityList(page);
    }

    /**
     * 获取事故事件详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geAccidentSecurityById")
    public ResponseBo getAccidentSecurityById(String id) {
        return accidentSecurityService.getAccidentSecurityById(id);
    }

    /**
     * 删除事故事件信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteAccidentSecurityById")
    public ResponseBo deleteAccidentSecurityById(@RequestBody String id) {
        if (id != null) {
            int result = accidentSecurityService.deleteByKey(id);
            return result == 0 ? ResponseBo.error("删除失败!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出核设施安全问题
     */
    @RequestMapping(value = "/exportAccidentSecurity", method = RequestMethod.GET)
    public void exportAccidentSecurity( @RequestParam(value = "depart", required = false) String depart,
                                  @RequestParam(value = "fac", required = false) String fac,
                                  @RequestParam(value = "facStatusTypeIds", required = false) String facStatusTypeIds,
                                  @RequestParam(value = "uminePlaceStatusTypeIds", required = false) String uminePlaceStatusTypeIds,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "start_date", required = false) String start_date,
                                  @RequestParam(value = "end_date", required = false) String end_date,
                                  @RequestParam(value = "typeIds", required = false) String typeIds,
                                  @RequestParam(value = "natureIds", required = false) String natureIds,
                                  HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!depart.isEmpty()) {
            list.add(new Condition("depart", depart));
        }
        if (!fac.isEmpty()) {
            list.add(new Condition("fac", fac));
        }
        if (!facStatusTypeIds.isEmpty()) {
            list.add(new Condition("facStatusTypeIds",  Stream.of(facStatusTypeIds).collect(toList())));
        }
        if (!uminePlaceStatusTypeIds.isEmpty()) {
            list.add(new Condition("uminePlaceStatusTypeIds",  Stream.of(uminePlaceStatusTypeIds).collect(toList())));
        }
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }
        if (!typeIds.isEmpty()) {
            list.add(new Condition("typeIds",  Stream.of(typeIds).collect(toList())));
        }
        if (!natureIds.isEmpty()) {
            list.add(new Condition("natureIds",  Stream.of(natureIds).collect(toList())));
        }


        Page page = new Page();
        page.setConditions(list);

        accidentSecurityService.exportAccidentSecurity(page,response);

    }
}
