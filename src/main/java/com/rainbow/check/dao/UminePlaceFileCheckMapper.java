package com.rainbow.check.dao;

import com.rainbow.check.domain.UminePlaceFileCheck;
import com.rainbow.check.domain.UminePlaceFileCheckExtend;
import com.rainbow.common.config.MyMapper;

import java.util.List;
import java.util.Map;

public interface UminePlaceFileCheckMapper extends MyMapper<UminePlaceFileCheck> {

    List<UminePlaceFileCheckExtend> getUminePlaceFileCheckList(Map<String,Object> map);

    UminePlaceFileCheck getUminePlaceFileCheckById(String id);
}