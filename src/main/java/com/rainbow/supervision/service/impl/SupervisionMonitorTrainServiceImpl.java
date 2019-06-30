package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionMonitorTrainMapper;
import com.rainbow.supervision.dao.SupervisionSastindMapper;
import com.rainbow.supervision.domain.SupervisionMonitorTrain;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.service.SupervisionMonitorTrainService;
import com.rainbow.supervision.service.SupervisionSastindService;
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
@Service("SupervisionMonitorTrainService")
public class SupervisionMonitorTrainServiceImpl extends BaseService<SupervisionMonitorTrain> implements SupervisionMonitorTrainService {

    @Autowired
    SupervisionMonitorTrainMapper monitorTrainMapper;

    @Override
    public int addTrainRecord(SupervisionMonitorTrain trainRecord) {
        trainRecord.setId(GuidHelper.getGuid());
        trainRecord.setCreateDate(new Date());
        trainRecord.setModifyDate(new Date());
        return monitorTrainMapper.insert(trainRecord);
    }

    @Override
    public int modifyTrainRecord(SupervisionMonitorTrain trainRecord) {
        trainRecord.setModifyDate(new Date());
        return monitorTrainMapper.updateByPrimaryKey(trainRecord);
    }

    @Override
    public ResponseBo getTrainRecordList(Page page){

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionMonitorTrain> list = monitorTrainMapper.getTrainRecordList(map);

        PageInfo<SupervisionMonitorTrain> pageInfo = new PageInfo<SupervisionMonitorTrain>(list);

        PagingEntity<SupervisionMonitorTrain> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
