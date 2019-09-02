package com.rainbow.statistics.service.impl;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.permit.dao.EquipPermitMapper;
import com.rainbow.statistics.dao.StatisticsMapper;
import com.rainbow.statistics.domain.*;
import com.rainbow.statistics.service.StatisticsService;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.UmineMountainMapper;
import com.rainbow.unit.dao.UmineplaceMapper;
import com.rainbow.unit.service.FacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("statisticsservice")
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsMapper statisticsMapper;

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Autowired
    FacMapper facMapper;

    @Autowired
    UmineMountainMapper umineMountainMapper;

    @Autowired
    UmineplaceMapper umineplaceMapper;

    @Autowired
    EquipPermitMapper equipPermitMapper;

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
            List<ResultObj> tempResult = new ArrayList<>();
            ResultObj objBig = new ResultObj();
            objBig.setName("2000年后");
            objBig.setValue(result.getBig());
            ResultObj objSmall = new ResultObj();
            objSmall.setName("2000年前");
            objSmall.setValue(result.getSmall());
            tempResult.add(objBig);
            tempResult.add(objSmall);
            return ResponseBo.ok(tempResult);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public ResponseBo getStatisticsResultByBoolean(SearchCondition condition) {
        List<ResultObj> result = statisticsMapper.getStatisticsResultByBoolean(condition);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public ResponseBo searchResultByPermitStageConditon(SearchCondition condition) {
        List<ResultObj> result = statisticsMapper.searchResultByPermitStageConditon(condition);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public ResponseBo searchResultByPermitDateConditon(SearchCondition condition) {

        List<String> yearList = DateUtils.getYearByStartAndEnd(condition.getStartDate(), condition.getEndDate());

        List<SystemConfig> systemConfigs = systemConfigMapper
                .getSystemConfigByTableName(condition.getConfigTableName());

        List<PermitReportDomainResult> result = statisticsMapper.searchResultByPermitDateConditon(condition);

        if (result != null) {

            List<PermitTypeNumber> tempResult = GetReportResult(result, yearList, systemConfigs);

            PermitReportResult p = new PermitReportResult();
            p.setYearDate(yearList);
            p.setNumberList(tempResult);
            return ResponseBo.ok(p);
        }
        return ResponseBo.error("获取失败");
    }

    private List<PermitTypeNumber> GetReportResult(List<PermitReportDomainResult> result, List<String> yearList,
            List<SystemConfig> systemConfigs) {

        List<PermitTypeNumber> list = new ArrayList<PermitTypeNumber>() {
        };

        for (SystemConfig config : systemConfigs) {

            PermitTypeNumber permitTypeNumber = new PermitTypeNumber();
            permitTypeNumber.setName(config.getValue());

            List<String> numerResult = new ArrayList<String>();

            // 循环年度，并根据年度和配置的名称找到数量，如果存在就放数量，如果不存在就为0
            for (String year : yearList) {

                int count = 0;
                for (PermitReportDomainResult permitReportDomainResult : result) {
                    if ((permitReportDomainResult.getReportYear() != null)
                            && (permitReportDomainResult.getValue() != null)) {
                        if ((permitReportDomainResult.getReportYear().equalsIgnoreCase(year))
                                && (permitReportDomainResult.getValue().equalsIgnoreCase(config.getValue()))) {
                            numerResult.add(permitReportDomainResult.getSumNum());
                            count++;
                        }
                    }
                }
                if (count == 0) {
                    numerResult.add("0");
                }
            }
            permitTypeNumber.setData(numerResult);
            list.add(permitTypeNumber);
        }
        return list;
    }

    @Override
    public ResponseBo getHomeNumber() {
        int facNum = facMapper.getFacCount();
        int umineplaceNum = umineplaceMapper.getUmineplaceCount();
        int uminemountainNum = umineMountainMapper.getUminmountainCount();
        int equipNum = equipPermitMapper.getEquipNum();

        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("fac", facNum);
        map.put("umineplace", umineplaceNum);
        map.put("uminemountain", uminemountainNum);
        map.put("equip", equipNum);
        return ResponseBo.ok(map);
    }

    @Override
    public ResponseBo searchSumReportByDateGroup(SearchCondition condition) {
        List<ResultObj> result = statisticsMapper.searchSumReportByDateGroup(condition);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public ResponseBo searchReportByDateAndSum(SearchCondition condition) {
        List<ResultObj> result = statisticsMapper.searchReportByDateAndSum(condition);
        if (result != null) {
            List<ResultObj> tempResult = new ArrayList<ResultObj>();

            List<String> yearList = DateUtils.getYearByStartAndEnd(condition.getStartDate(), condition.getEndDate());
            for (String year : yearList) {
                int count = 0;

                for (ResultObj obj : result) {
                    if (obj.getName().equalsIgnoreCase(year)) {
                        tempResult.add(obj);
                        count++;
                    }
                }
                if (count == 0) {
                    ResultObj t = new ResultObj();
                    t.setName(year);
                    t.setValue(0);
                    tempResult.add(t);
                }
            }
            return ResponseBo.ok(tempResult);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public ResponseBo searchResultByTypeConditon(SearchCondition condition) {
        List<ResultObj> result = statisticsMapper.searchResultByTypeConditon(condition);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public ResponseBo searchResultByDateConditon(SearchCondition condition) {
        List<String> yearList = DateUtils.getYearByStartAndEnd(condition.getStartDate(), condition.getEndDate());

        List<SystemConfig> systemConfigs = systemConfigMapper
                .getSystemConfigByTableName(condition.getConfigTableName());

        List<PermitReportDomainResult> result = statisticsMapper.searchResultByDateConditon(condition);

        if (result != null) {

            List<PermitTypeNumber> tempResult = GetReportResult(result, yearList, systemConfigs);

            PermitReportResult p = new PermitReportResult();
            p.setYearDate(yearList);
            p.setNumberList(tempResult);
            return ResponseBo.ok(p);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public ResponseBo searchResultByStatusAndType(SearchCondition condition) {

        List<SystemConfig> xList = systemConfigMapper.getSystemConfigByTableName("config_security_reform_status");

        List<String> list = new ArrayList<String>();

        for (SystemConfig config : xList) {
            list.add(config.getValue());
        }

        List<SystemConfig> yList = systemConfigMapper.getSystemConfigByTableName(condition.getConfigTableName());

        List<PermitReportDomainResult> result = statisticsMapper.searchResultByStatusAndType(condition);

        if (result != null) {

            List<PermitTypeNumber> tempResult = GetReportResult(result, list, yList);

            PermitReportResult p = new PermitReportResult();
            p.setYearDate(list);
            p.setNumberList(tempResult);
            return ResponseBo.ok(p);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public List<ResultObj> statisticsFacilitiesByRegion() {
        Map<String, List<String>> conditon = new HashMap<String, List<String>>();

        conditon.put("华北", Arrays.asList("北京", "天津", "河北", "河南", "山西", "内蒙古"));
        conditon.put("华东", Arrays.asList("上海", "江苏", "浙江", "安徽", "福建", "江西", "山东"));
        conditon.put("华南", Arrays.asList("湖北", "湖南", "广东", "广西", "海南"));
        conditon.put("西北", Arrays.asList("陕西", "甘肃", "青海", "宁夏", "新疆"));
        conditon.put("西南", Arrays.asList("重庆", "四川", "贵州", "云南", "西藏"));
        conditon.put("东北", Arrays.asList("辽宁", "吉林", "黑龙江"));

        List<ResultObj> temp = statisticsMapper.statisticsFacilitiesByRegion();
        List<ResultObj> result = new ArrayList<>();
        for (String key : conditon.keySet()) {
            ResultObj obj = new ResultObj();
            obj.setName(key);
            List<String> regions = conditon.get(key);
            List<ResultObj> regionObjs = temp.stream().filter(item -> regions.contains(item.getName()))
                    .collect(Collectors.toList());
            // obj.setValue(count);
            int value = regionObjs.stream().mapToInt(ResultObj::getValue).sum();
            obj.setValue(value);
            result.add(obj);
        }
        return result;
    }
}