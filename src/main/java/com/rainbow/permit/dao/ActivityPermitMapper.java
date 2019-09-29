package com.rainbow.permit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.ActivityPermitExtend;
import com.rainbow.permit.domain.EquipPermit;

import java.util.List;
import java.util.Map;

public interface ActivityPermitMapper extends MyMapper<ActivityPermit> {

    List<ActivityPermitExtend> getActivityPermitList(Map<String, Object> map);

    ActivityPermit getActivityPermitById(String id);

    int verifyDuplication(Map<String, Object> params);
}