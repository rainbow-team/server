package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.Welder;

import java.util.List;
import java.util.Map;

public interface WelderMapper extends MyMapper<Welder> {

    List<Welder> getWelderList(Map<String,Object> map);

    Welder getWelderById(String id);
}