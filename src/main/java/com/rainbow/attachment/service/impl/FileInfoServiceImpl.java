package com.rainbow.attachment.service.impl;

import com.rainbow.attachment.dao.FileInfoMapper;
import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.config.RainbowProperties;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.StrUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;


/**
 * Created by 13260 on 2019/5/16.
 */
@Service("FileInfoService")
public class FileInfoServiceImpl extends BaseService<FileInfo> implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private RainbowProperties rainbowProperties;

    @Override
    public ResponseBo upload(MultipartFile multifile){

        String guid = UUID.randomUUID().toString();

//        String fromID = request.getParameter("id");

//        Multipart part = new Multipart();
//        MultipartFile multifile = part.getUploadFile(request);
        if (null == multifile) {
            return ResponseBo.error();
        }
        String ext = null;
        String storeFile = null;
        String fileName =null;
        String fileSavePath ="";

        try {
            fileName = StrUtil.isNullOrEmpty(fileName) ? multifile.getOriginalFilename() : fileName;
            fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");
            ext = FilenameUtils.getExtension(fileName); //fileName.split("\\.")[1];
            storeFile = guid + "." + ext;

            // 保存文件
            String storageFolder = GetFileStorageFolder(guid);
            fileSavePath = storageFolder + storeFile;
            File file = new File(storageFolder);

            if (!file.exists()) {
                file.mkdirs();
            }

            File file1 = new File(fileSavePath);
            multifile.transferTo(file1);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileinfoId(guid);
        fileInfo.setFileinfoFileType(ext);
        fileInfo.setFileinfoServerFileName(storeFile);
        fileInfo.setFileinfoClientFileName(fileName);
        fileInfo.setFileinfoServerPath(fileSavePath);

        fileInfoMapper.insert(fileInfo);


        return ResponseBo.ok(guid);
    }

    public  String GetFileStorageFolder(String actualFile) {
//        RainbowProperties rp = new RainbowProperties();

        String  webInfPath = rainbowProperties.getUploadFolder();

        String path = String.format("%s%s/%s/", webInfPath, actualFile.charAt(0), actualFile.charAt(1));
        return path;
    }

    @Override
    public ResponseBo getFileList(String id){

        Example example = new Example(FileInfo.class);
        example.createCriteria().andEqualTo("fileinfoRefId",id);
        List<FileInfo> list =fileInfoMapper.selectByExample(example);
        return ResponseBo.ok(list);

    }


}
