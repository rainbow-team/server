package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.SupervisionSastind;

import java.util.List;
import java.util.Map;

public interface SupervisionSastindMapper extends MyMapper<SupervisionSastind> {

    List<SupervisionSastind> getSastindList(Map<String,Object> map);

}