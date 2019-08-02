package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisorTrainRecord;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface SupervisorTrainRecordService extends IService<SupervisorTrainRecord> {

    int addSupervisionTrainRecord(SupervisorTrainRecord record);

    ResponseBo getSupervisionTrainRecordList(Page page);

    void updateSupervisorExpireDate(SupervisorTrainRecord record);
}
