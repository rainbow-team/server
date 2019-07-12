package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisionProduceTrain;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface SupervisionProduceTrainService extends IService<SupervisionProduceTrain> {

    int addProduceTrainRecord(SupervisionProduceTrain trainRecord);

    int modifyProduceTrainRecord(SupervisionProduceTrain trainRecord);

    ResponseBo getProduceTrainRecordList(Page page);

    ResponseBo getProduceTrainRecordById(String id);
}
