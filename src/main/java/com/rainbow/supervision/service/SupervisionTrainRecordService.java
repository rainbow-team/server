package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisionLaw;
import com.rainbow.supervision.domain.SupervisionTrainRecord;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface SupervisionTrainRecordService extends IService<SupervisionTrainRecord> {

    int addSupervisionTrainRecord(SupervisionTrainRecord record);

    ResponseBo getSupervisionTrainRecordList(Page page);
}
