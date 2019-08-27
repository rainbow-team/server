package com.rainbow.dataMigration.controller;

import com.rainbow.dataMigration.service.DataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public void exportData(@RequestParam(value = "type", required = false) String type,HttpServletResponse response){
        dataMigrationService.exportData(type,response);
    }
}
