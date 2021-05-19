package com.rainbow.permit.controller;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.permit.service.PermitPublishScopeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: rainbow
 * @description: 许可信息知悉范围服务
 * @author: daiyy
 * @create: 2021-05-12 20:43
 **/
@RestController
@RequestMapping("permitpublishscope")
public class PermitPublishScopeController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PermitPublishScopeService service;

    @GetMapping("/getListByPermitId")
    public ResponseBo getListByPermitId(String id) {
        return service.getListByPermitId(id);
    }


}
