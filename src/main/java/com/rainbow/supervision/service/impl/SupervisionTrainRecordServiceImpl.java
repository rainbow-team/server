package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionLawMapper;
import com.rainbow.supervision.dao.SupervisionTrainRecordMapper;
import com.rainbow.supervision.domain.SupervisionLaw;
import com.rainbow.supervision.domain.SupervisionTrainRecord;
import com.rainbow.supervision.service.SupervisionLawService;
import com.rainbow.supervision.service.SupervisionProduceTrainService;
import com.rainbow.supervision.service.SupervisionTrainRecordService;
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
@Service("SupervisionTrainRecordService")
public class SupervisionTrainRecordServiceImpl extends BaseService<SupervisionTrainRecord> implements SupervisionTrainRecordService {

    @Autowired
    SupervisionTrainRecordMapper trainRecordMapper;

    @Override
    public int addSupervisionTrainRecord(SupervisionTrainRecord record) {
        record.setId(GuidHelper.getGuid());
        return trainRecordMapper.insert(record);
    }

    @Override
    public ResponseBo getSupervisionTrainRecordList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionTrainRecord> list = trainRecordMapper.getTrainRecordList(map);

        PageInfo<SupervisionTrainRecord> pageInfo = new PageInfo<SupervisionTrainRecord>(list);

        PagingEntity<SupervisionTrainRecord> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
