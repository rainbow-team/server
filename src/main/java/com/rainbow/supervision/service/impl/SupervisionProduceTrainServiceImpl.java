package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionProduceTrainMapper;
import com.rainbow.supervision.domain.SupervisionMonitorTrain;
import com.rainbow.supervision.domain.SupervisionProduceTrain;
import com.rainbow.supervision.service.SupervisionProduceTrainService;
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
@Service("SupervisionProduceTrainService")
public class SupervisionProduceTrainServiceImpl extends BaseService<SupervisionProduceTrain> implements SupervisionProduceTrainService {

    @Autowired
    SupervisionProduceTrainMapper produceTrainMapper;

    @Override
    public int addTrainRecord(SupervisionProduceTrain trainRecord) {
        trainRecord.setId(GuidHelper.getGuid());
        trainRecord.setCreateDate(new Date());
        trainRecord.setModifyDate(new Date());
        return produceTrainMapper.insert(trainRecord);
    }

    @Override
    public int modifyTrainRecord(SupervisionProduceTrain trainRecord) {
        trainRecord.setModifyDate(new Date());
        return produceTrainMapper.updateByPrimaryKey(trainRecord);
    }

    @Override
    public ResponseBo getTrainRecordList(Page page){

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionProduceTrain> list = produceTrainMapper.getTrainRecordList(map);

        PageInfo<SupervisionProduceTrain> pageInfo = new PageInfo<SupervisionProduceTrain>(list);

        PagingEntity<SupervisionProduceTrain> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
