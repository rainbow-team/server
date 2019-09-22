package com.rainbow.dataMigration.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.dataMigration.service.DataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 13260 on 2019/8/27.
 */
@RestController
@RequestMapping("/dataMigration")
public class DataMigrationController {

    @Autowired
    DataMigrationService dataMigrationService;

    @RequestMapping(value = "/exportData", method = RequestMethod.GET)
    @SystemLog(description="数据迁移导出")
    public void exportData(@RequestParam(value = "type", required = false) String type,HttpServletResponse response){
        dataMigrationService.exportData(type,response);
    }

    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description="数据迁移导入")
    public ResponseBo importData(HttpServletRequest request) {
        return dataMigrationService.importData(request);
    }

}
