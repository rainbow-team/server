package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.ProduceTrainMapper;
import com.rainbow.supervision.domain.SupervisionProduceTrain;
import com.rainbow.supervision.service.ProduceTrainService;
import com.rainbow.system.dao.UserMapper;
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
public class ProduceTrainServiceImpl extends BaseService<SupervisionProduceTrain> implements ProduceTrainService {

    @Autowired
    ProduceTrainMapper produceTrainMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addProduceTrainRecord(SupervisionProduceTrain trainRecord) {
        trainRecord.setId(GuidHelper.getGuid());
        trainRecord.setCreateDate(new Date());
        trainRecord.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(trainRecord.getAttachmentList(),trainRecord.getId());
        return produceTrainMapper.insert(trainRecord);
    }

    @Override
    public int modifyProduceTrainRecord(SupervisionProduceTrain trainRecord) {
        trainRecord.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(trainRecord.getAttachmentList(),trainRecord.getId());
        return produceTrainMapper.updateByPrimaryKey(trainRecord);
    }

    @Override
    public ResponseBo getProduceTrainRecordById(String id) {
        SupervisionProduceTrain result =  produceTrainMapper.selectByPrimaryKey(id);
        //创建人
        String name = userMapper.getUserNameById(result.getCreatorId());
        result.setCreatorName(name);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getProduceTrainRecordList(Page page){

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionProduceTrain> list = produceTrainMapper.getProduceTrainRecordList(map);

        PageInfo<SupervisionProduceTrain> pageInfo = new PageInfo<SupervisionProduceTrain>(list);

        PagingEntity<SupervisionProduceTrain> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
