package com.rainbow.statistics.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.statistics.domain.ResultObj;
import com.rainbow.statistics.domain.SearchCondition;
import com.rainbow.statistics.domain.YearResultObj;

import java.util.List;
import java.util.Map;

public interface StatisticsMapper extends MyMapper<ResultObj> {

    List<ResultObj> searchResultByCommonConditon(SearchCondition condition);

    YearResultObj getStatisticsResultByYear(SearchCondition condition);

    List<ResultObj> getStatisticsResultByBoolean(SearchCondition condition);

}