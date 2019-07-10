package com.rainbow.check.dao;

import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.EquipCheck;
import com.rainbow.common.config.MyMapper;

import java.util.List;
import java.util.Map;

public interface ActivityCheckMapper extends MyMapper<ActivityCheck> {

    ActivityCheck getActivityCheckById(String id);

    List<ActivityCheck> getActivityCheckList(Map<String,Object> map);

    int deleteActivityCheckById(String id);

    //根据核活动审评的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getActivityCheckRelationCount(String id);
}