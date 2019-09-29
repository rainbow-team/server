package com.rainbow.permit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.UminePlacePermit;
import com.rainbow.permit.domain.UminePlacePermitExtend;

import java.util.List;
import java.util.Map;

public interface UminePlacePermitMapper extends MyMapper<UminePlacePermit> {


    List<UminePlacePermitExtend> getUminePlacePermitList(Map<String,Object> map);

    UminePlacePermit getUminePlacePermitById(String id);
}