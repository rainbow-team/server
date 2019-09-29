package com.rainbow.check.dao;

import com.rainbow.check.domain.UmineMountainFileCheck;
import com.rainbow.check.domain.UmineMountainFileCheckExtend;
import com.rainbow.check.domain.UminePlaceFileCheck;
import com.rainbow.common.config.MyMapper;

import java.util.List;
import java.util.Map;

public interface UmineMountainFileCheckMapper extends MyMapper<UmineMountainFileCheck> {

    List<UmineMountainFileCheckExtend> getUmineMountainFileCheckList(Map<String,Object> map);

    UmineMountainFileCheck getUmineMountainFileCheckById(String id);
}