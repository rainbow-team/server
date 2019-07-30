package com.rainbow.statistics.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.statistics.domain.SearchCondition;

import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface StatisticsService {

    ResponseBo getStatisticsResultByCommonCondition(SearchCondition condition);

    ResponseBo getStatisticsResultByYear(SearchCondition condition);


    ResponseBo getStatisticsResultByBoolean(SearchCondition condition);

    ResponseBo searchResultByPermitStageConditon(SearchCondition condition);

    ResponseBo searchResultByPermitDateConditon(SearchCondition condition);

    ResponseBo getHomeNumber();


    ResponseBo searchSumReportByDateGroup(SearchCondition condition);

    ResponseBo searchReportByDateAndSum(SearchCondition condition);


    ResponseBo searchResultByTypeConditon(SearchCondition condition);

    ResponseBo searchResultByDateConditon(SearchCondition condition);
}
