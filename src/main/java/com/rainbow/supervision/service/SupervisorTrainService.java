package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisorTrain;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface SupervisorTrainService extends IService<SupervisorTrain> {

    int addMonitorTrain(SupervisorTrain trainRecord);

    int modifyMonitorTrain(SupervisorTrain trainRecord);

    ResponseBo getMonitorTrainList(Page page);

    ResponseBo getMonitorTrainById(String id);

}
