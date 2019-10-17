package com.rainbow.config.controller;

import com.rainbow.common.controller.BaseController;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.config.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 13260 on 2019/9/19.
 */
@RestController
@RequestMapping("/SystemLog")
public class SystemLogController extends BaseController {

    @Autowired
    ISystemLogService systemLogService;

    @PostMapping("/getSystemLogList")
    public ResponseBo getSystemLogList(@RequestBody Page page) {
        return systemLogService.getSystemLogList(page);
    }

    @PostMapping("/deleteSystemLog")
    public ResponseBo deleteSystemLog(){

        return systemLogService.deleteSystemLog();
    }

}
