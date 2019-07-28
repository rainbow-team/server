package com.rainbow.statistics.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.statistics.domain.PermitReportDomainResult;
import com.rainbow.statistics.domain.ResultObj;
import com.rainbow.statistics.domain.SearchCondition;
import com.rainbow.statistics.domain.YearResultObj;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Map;

public interface StatisticsMapper extends MyMapper<ResultObj> {

    List<ResultObj> searchResultByCommonConditon(SearchCondition condition);

    YearResultObj getStatisticsResultByYear(SearchCondition condition);

    List<ResultObj> getStatisticsResultByBoolean(SearchCondition condition);

    List<ResultObj> searchResultByPermitStageConditon(SearchCondition condition);

    List<PermitReportDomainResult> searchResultByPermitDateConditon(SearchCondition condition);

    //根据表明，按照时间进行分组统计
    List<ResultObj> searchSumReportByDateGroup(SearchCondition condition);

    List<ResultObj> searchReportByDateAndSum(SearchCondition condition);

}