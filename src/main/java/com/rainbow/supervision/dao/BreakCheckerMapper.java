package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.extend.BreakCheckerExtend;

import java.util.List;
import java.util.Map;

public interface BreakCheckerMapper extends MyMapper<BreakChecker> {

    List<BreakCheckerExtend> getBreakCheckerList(Map<String,Object> map);

    BreakCheckerExtend getBreakCheckerById(String id);
}