package com.rainbow.check.dao;

import com.rainbow.check.domain.UmineMountainCheck;
import com.rainbow.check.domain.UminePlaceCheck;
import com.rainbow.common.config.MyMapper;

import java.util.List;
import java.util.Map;

public interface UmineMountainCheckMapper extends MyMapper<UmineMountainCheck> {

    UmineMountainCheck getUmineMountainCheckById(String id);

    List<UmineMountainCheck> getUmineMountainCheckList(Map<String,Object> map);

    int deleteUmineMountainCheckById(String id);

    //根据铀尾矿山审评的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getUmineMountainCheckRelationCount(String id);
}