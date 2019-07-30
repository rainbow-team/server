package com.rainbow.statistics.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.service.AccidentSecurityService;
import com.rainbow.statistics.domain.SearchCondition;
import com.rainbow.statistics.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by 13260 on 2019/5/11.
 * 统计分析模块
 */
@RestController
@RequestMapping("statistics")
public class StatisticsController {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    StatisticsService statisticsService;

    /**
     * 根据表名，要归类的属性以及属性对应的静态表查询 分组的和
     *
     * @param
     * @return
     */
    @PostMapping("/getStatisticsResultByCondition")
    public ResponseBo getStatisticsResultByCondition(@RequestBody SearchCondition condition) {

        if (condition != null) {
            return statisticsService.getStatisticsResultByCommonCondition(condition);
        }
        else {
            return ResponseBo.error("查询错误");
        }
    }

    /**
     * 根据表名和要分组的属性查询建造年代的统计信息
     *
     * @param
     * @return
     */
    @PostMapping("/getStatisticsResultByYear")
    public ResponseBo getStatisticsResultByYear(@RequestBody SearchCondition condition) {

        if (condition != null) {
            return statisticsService.getStatisticsResultByYear(condition);
        }
        else {
            return ResponseBo.error("查询错误");
        }
    }


    /**
     * 根据表名和要分组的属性查询建造年代的统计信息
     *
     * @param
     * @return
     */
    @PostMapping("/getStatisticsResultByBoolean")
    public ResponseBo getStatisticsResultByBoolean(@RequestBody SearchCondition condition) {

        if (condition != null) {
            return statisticsService.getStatisticsResultByBoolean(condition);
        }
        else {
            return ResponseBo.error("查询错误");
        }
    }


    /**
     * 核安全许可按类型统计
     *
     * @param
     * @return
     */
    @PostMapping("/searchResultByPermitStageConditon")
    public ResponseBo searchResultByPermitStageConditon(@RequestBody SearchCondition condition) {

        if (condition != null) {
            return statisticsService.searchResultByPermitStageConditon(condition);
        }
        else {
            return ResponseBo.error("查询错误");
        }
    }

    /**
     * 核安全许可按时间统计
     *
     * @param
     * @return
     */
    @PostMapping("/searchResultByPermitDateConditon")
    public ResponseBo searchResultByPermitDateConditon(@RequestBody SearchCondition condition) {

        if (condition != null) {
            return statisticsService.searchResultByPermitDateConditon(condition);
        }
        else {
            return ResponseBo.error("查询错误");
        }
    }

    @PostMapping("/getHomeNumer")
    public ResponseBo getHomeNumer(){
        return statisticsService.getHomeNumber();
    }

    /**
     * 获取历年的事故事件的统计信息
     *
     * @param
     * @return
     */
    @PostMapping("/searchSumReportByDateGroup")
    public ResponseBo searchSumReportByDateGroup(@RequestBody SearchCondition condition) {
        if (condition != null) {
            return statisticsService.searchSumReportByDateGroup(condition);
        }
        else {
            return ResponseBo.error("查询错误");
        }
    }

    /**
     * 获取核活动许可统计信息
     *
     * @param
     * @return
     */
    @PostMapping("/searchReportByDateAndSum")
    public ResponseBo searchReportByDateAndSum(@RequestBody SearchCondition condition) {
        if (condition != null) {
            return statisticsService.searchReportByDateAndSum(condition);
        }
        else {
            return ResponseBo.error("查询错误");
        }
    }


    /**
     * 核安全许可按类型统计
     *
     * @param
     * @return
     */
    @PostMapping("/searchResultByTypeConditon")
    public ResponseBo searchResultByTypeConditon(@RequestBody SearchCondition condition) {

        if (condition != null) {
            return statisticsService.searchResultByTypeConditon(condition);
        }
        else {
            return ResponseBo.error("查询错误");
        }
    }

    /**
     * 核安全许可按时间统计
     *
     * @param
     * @return
     */
    @PostMapping("/searchResultByDateConditon")
    public ResponseBo searchResultByDateConditon(@RequestBody SearchCondition condition) {

        if (condition != null) {
            return statisticsService.searchResultByPermitDateConditon(condition);
        }
        else {
            return ResponseBo.error("查询错误");
        }
    }
}
