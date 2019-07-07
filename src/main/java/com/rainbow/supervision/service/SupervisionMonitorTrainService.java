package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisionMonitorTrain;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface SupervisionMonitorTrainService extends IService<SupervisionMonitorTrain> {

    int addMonitorTrain(SupervisionMonitorTrain trainRecord);

    int modifyMonitorTrain(SupervisionMonitorTrain trainRecord);

    ResponseBo getMonitorTrainList(Page page);

    ResponseBo getMonitorTrainById(String id);
}
