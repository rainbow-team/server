package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.Welder;
import com.rainbow.supervision.domain.extend.WelderExtend;

import java.util.List;
import java.util.Map;

public interface WelderMapper extends MyMapper<Welder> {

    List<WelderExtend> getWelderList(Map<String,Object> map);

    WelderExtend getWelderById(String id);
}