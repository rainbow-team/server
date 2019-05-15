package com.rainbow.attachment.controller;

import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.ResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 13260 on 2019/5/16.
 */
@RestController
@RequestMapping("/fileInfo")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping(value = "/upload")
    @ResponseBody
    public ResponseBo upload(@RequestParam("uploadFile") MultipartFile file){
        return fileInfoService.upload(file);
    }
}
