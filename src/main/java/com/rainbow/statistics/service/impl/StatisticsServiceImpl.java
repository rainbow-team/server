package com.rainbow.statistics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.security.dao.AccidentSecurityMapper;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.service.AccidentSecurityService;
import com.rainbow.statistics.dao.StatisticsMapper;
import com.rainbow.statistics.domain.ResultObj;
import com.rainbow.statistics.domain.SearchCondition;
import com.rainbow.statistics.domain.YearResultObj;
import com.rainbow.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("statisticsservice")
public class StatisticsServiceImpl implements StatisticsService {


    @Autowired
    StatisticsMapper statisticsMapper;

    @Override
    public ResponseBo getStatisticsResultByCommonCondition(SearchCondition condition) {
        List<ResultObj> result = statisticsMapper.searchResultByCommonConditon(condition);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public ResponseBo getStatisticsResultByYear(SearchCondition condition) {
        YearResultObj result = statisticsMapper.getStatisticsResultByYear(condition);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }
}