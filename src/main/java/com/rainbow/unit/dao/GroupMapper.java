package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.domain.GroupExtend;

import java.util.List;
import java.util.Map;

public interface GroupMapper extends MyMapper<Group> {

    List<Group> getGroupList(Map<String,Object> map);

    GroupExtend getGroupById(String groupId);

    int deleteGroupById(String id);

    //根据集团的主键寻找关联的记录，返回记录的和，如果不存在关联关系则返回1，如果存在关联关系则返回0
    Object getGroupRelationCount(String id);

    String getGroupIdByName(String name);

}