package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.SupervisionWelder;

import java.util.List;
import java.util.Map;

public interface SupervisionWelderMapper extends MyMapper<SupervisionWelder> {

    List<SupervisionWelder> getWelderList(Map<String,Object> map);

    SupervisionWelder getWelderById(String id);
}