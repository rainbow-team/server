package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.SupervisionExpert;

import java.util.List;
import java.util.Map;

public interface BreakCheckerMapper extends MyMapper<BreakChecker> {

    List<BreakChecker> getBreakCheckerList(Map<String,Object> map);

    BreakChecker getBreakCheckerById(String id);
}