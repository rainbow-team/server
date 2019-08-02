package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisorTrainMapper;
import com.rainbow.supervision.domain.SupervisorTrain;
import com.rainbow.supervision.service.SupervisorTrainService;
import com.rainbow.system.dao.UserMapper;
import com.rainbow.system.domain.SystemUser;
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
public class SupervisorTrainServiceImpl extends BaseService<SupervisorTrain> implements SupervisorTrainService {

    @Autowired
    SupervisorTrainMapper monitorTrainMapper;

    @Autowired
    private com.rainbow.attachment.dao.FileInfoMapper FileInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addMonitorTrain(SupervisorTrain trainRecord) {
        trainRecord.setId(GuidHelper.getGuid());
        trainRecord.setCreateDate(new Date());
        trainRecord.setModifyDate(new Date());

        updateFileInfoByIds(trainRecord);

        return monitorTrainMapper.insert(trainRecord);
    }

    @Override
    public int modifyMonitorTrain(SupervisorTrain trainRecord) {
        trainRecord.setModifyDate(new Date());

        updateFileInfoByIds(trainRecord);
        return monitorTrainMapper.updateByPrimaryKey(trainRecord);
    }

    public void updateFileInfoByIds(SupervisorTrain trainRecord){
        if(trainRecord.getAttachmentList().size()>0){
            Map<String,Object> map = new HashMap<>();
            map.put("id",trainRecord.getId());
            map.put("fileIds",trainRecord.getAttachmentList());
            FileInfoMapper.updateFileInfoByIds(map);
        }
    }

    @Override
    public ResponseBo getMonitorTrainList(Page page){

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisorTrain> list = monitorTrainMapper.getMonitorTrainList(map);

        PageInfo<SupervisorTrain> pageInfo = new PageInfo<SupervisorTrain>(list);

        PagingEntity<SupervisorTrain> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getMonitorTrainById(String id){

        SupervisorTrain result =  monitorTrainMapper.selectByPrimaryKey(id);
        ////创建人
        //SystemUser user = userMapper.selectByPrimaryKey(result.getCreatorId());
        //result.setCreatorName(user.getUsername());

        return ResponseBo.ok(result);
    }
}
