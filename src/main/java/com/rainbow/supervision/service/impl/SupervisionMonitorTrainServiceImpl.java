package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.dao.FileInfoMapper;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionMonitorTrainMapper;
import com.rainbow.supervision.domain.SupervisionMonitorTrain;
import com.rainbow.supervision.service.SupervisionMonitorTrainService;
import com.rainbow.system.dao.UserMapper;
import com.rainbow.system.domain.User;
import com.rainbow.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private com.rainbow.attachment.dao.FileInfoMapper FileInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addTrainRecord(SupervisionMonitorTrain trainRecord) {
        trainRecord.setId(GuidHelper.getGuid());
        trainRecord.setCreateDate(new Date());
        trainRecord.setModifyDate(new Date());

        updateFileInfoByIds(trainRecord);

        return monitorTrainMapper.insert(trainRecord);
    }

    @Override
    public int modifyTrainRecord(SupervisionMonitorTrain trainRecord) {
        trainRecord.setModifyDate(new Date());

        updateFileInfoByIds(trainRecord);
        return monitorTrainMapper.updateByPrimaryKey(trainRecord);
    }

    public void updateFileInfoByIds(SupervisionMonitorTrain trainRecord){
        if(trainRecord.getAttachmentList().size()>0){
            Map<String,Object> map = new HashMap<>();
            map.put("id",trainRecord.getId());
            map.put("fileIds",trainRecord.getAttachmentList());
            FileInfoMapper.updateFileInfoByIds(map);
        }
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

    @Override
    public ResponseBo getTrainRecordById(String id){

        SupervisionMonitorTrain result =  monitorTrainMapper.selectByPrimaryKey(id);
        //创建人
        String name = userMapper.getUserNameById(result.getCreatorId());
        result.setCreatorName(name);

        return ResponseBo.ok(result);
    }
}
