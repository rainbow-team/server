package com.rainbow.check.dao;

import com.rainbow.check.domain.EquipFileCheck;
import com.rainbow.check.domain.EquipFileCheckExtend;
import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.common.config.MyMapper;

import java.util.List;
import java.util.Map;

public interface EquipFileCheckMapper extends MyMapper<EquipFileCheck> {

    List<EquipFileCheckExtend> getEquipFileCheckList(Map<String,Object> map);

    EquipFileCheck getEquipFileCheckById(String id);
}