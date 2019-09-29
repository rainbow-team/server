package com.rainbow.check.dao;

import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.check.domain.FacFileCheckExtend;
import com.rainbow.common.config.MyMapper;
import com.rainbow.permit.domain.ActivityPermit;

import java.util.List;
import java.util.Map;

public interface FacFileCheckMapper extends MyMapper<FacFileCheck> {

    List<FacFileCheckExtend> getFacFileCheckList(Map<String,Object> map);

    FacFileCheck getFacFileCheckById(String id);
}