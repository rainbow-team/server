package com.rainbow.security.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.service.FacSecurityService;
import com.rainbow.security.service.UminePlaceSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseBo deleteUminePlaceSecurityByIds(@RequestBody String id) {
        if (id != null) {
            int result = uminePlaceSecurityService.deleteByKey(id);
            return result == 0 ? ResponseBo.error("删除失败!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }
}
