package com.rainbow.attachment.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.attachment.domain.FileInfo;

import java.util.List;
import java.util.Map;

public interface FileInfoMapper extends MyMapper<FileInfo> {

    int updateFileInfoByIds(Map<String,Object> map);

}