package com.rainbow.permit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.permit.domain.UmineMountainPermit;
import com.rainbow.permit.domain.UmineMountainPermitExtend;
import com.rainbow.permit.domain.UminePlacePermit;

import java.util.List;
import java.util.Map;

public interface UmineMountainPermitMapper extends MyMapper<UmineMountainPermit> {


    List<UmineMountainPermitExtend> getUmineMountainPermitList(Map<String,Object> map);

    UmineMountainPermit getUmineMountainPermitById(String id);
}