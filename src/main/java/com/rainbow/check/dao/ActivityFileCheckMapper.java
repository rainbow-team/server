package com.rainbow.check.dao;

import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.check.domain.ActivityFileCheckExtend;
import com.rainbow.check.domain.EquipFileCheck;
import com.rainbow.common.config.MyMapper;

import java.util.List;
import java.util.Map;

public interface ActivityFileCheckMapper extends MyMapper<ActivityFileCheck> {

    List<ActivityFileCheckExtend> getActivityFileCheckList(Map<String,Object> map);

    ActivityFileCheck getActivityFileCheckById(String id);

}