package com.rainbow.monitor.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface WitnessMonitorService extends IService<WitnessMonitor> {

    int addWitnessMonitor(WitnessMonitor witnessMonitor);

    int modifyWitnessMonitor(WitnessMonitor witnessMonitor);

    ResponseBo getWitnessMonitorList(Page page);

    ResponseBo getWitnessMonitorById(String id);

    void exportWitnessMonitor(Page page, HttpServletResponse response);

    ResponseBo importData(HttpServletRequest request);
}
