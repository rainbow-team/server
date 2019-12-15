package com.rainbow.attachment.controller;

import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.ResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/5/16.
 */
@RestController
@RequestMapping("/fileInfo")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping(value = "/upload")
    public ResponseBo upload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        return fileInfoService.upload(file,request);
    }

    @GetMapping("/delete")
    public ResponseBo deleteFileById(String id){
        int num= fileInfoService.deleteByKey(id);

        return ResponseBo.ok();
    }

    @GetMapping("/getFileListById")
    public ResponseBo getFileList(String id){
        return fileInfoService.getFileList(id);
    }

    @GetMapping("/download")
    public void downloadAccessoryByid(String id,int type,HttpServletResponse response){
        fileInfoService.downloadAccessoryByid(id,type,response);
    }

    @PostMapping(value = "/saveFileLog")
    public void saveFileLog(@RequestBody  Map<String,String> map){
        fileInfoService.saveFileLog(map);
    }
}
