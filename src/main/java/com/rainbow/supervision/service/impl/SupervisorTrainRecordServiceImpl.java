package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionTrainRecordMapper;
import com.rainbow.supervision.dao.SupervisorMapper;
import com.rainbow.supervision.domain.SupervisorTrainRecord;
import com.rainbow.supervision.service.SupervisorTrainRecordService;
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
public class SupervisorTrainRecordServiceImpl extends BaseService<SupervisorTrainRecord> implements SupervisorTrainRecordService {

    @Autowired
    SupervisionTrainRecordMapper trainRecordMapper;

    @Autowired
    SupervisorMapper supervisorMapper;

    @Override
    public int addSupervisionTrainRecord(SupervisorTrainRecord record) {
        record.setId(GuidHelper.getGuid());
        return trainRecordMapper.insert(record);
    }

    @Override
    public ResponseBo getSupervisionTrainRecordList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisorTrainRecord> list = trainRecordMapper.getTrainRecordList(map);

        PageInfo<SupervisorTrainRecord> pageInfo = new PageInfo<SupervisorTrainRecord>(list);

        PagingEntity<SupervisorTrainRecord> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public void updateSupervisorExpireDate(SupervisorTrainRecord record) {
        Date expireDate = trainRecordMapper.getMaxExpireDateBySupervisorId(record.getSupervisorId());
       // int result = supervisorMapper.updateExpireDateById(record.getSupervisorId(), expireDate);
    }
}
