package com.rainbow.attachment.service;

import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 13260 on 2019/5/16.
 */
public interface FileInfoService extends IService<FileInfo> {

    ResponseBo upload(MultipartFile file);
}
