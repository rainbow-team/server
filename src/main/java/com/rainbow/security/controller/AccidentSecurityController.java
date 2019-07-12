package com.rainbow.security.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.service.AccidentSecurityService;
import com.rainbow.security.service.UminePlaceSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseBo add(@RequestBody AccidentSecurity uminePlaceSecurity) {
        int result = accidentSecurityService.addAccidentSecurity(uminePlaceSecurity);

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
    public ResponseBo modify(@RequestBody AccidentSecurity uminePlaceSecurity) {

        int result = accidentSecurityService.modifyAccidentSecurity(uminePlaceSecurity);
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
     * @param ids
     * @return
     */
    @PostMapping("/deleteAccidentSecurityByIds")
    public ResponseBo deleteAccidentSecurityByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            accidentSecurityService.batchDelete(ids, "id", AccidentSecurity.class);
        }
        return ResponseBo.ok();
    }
}
