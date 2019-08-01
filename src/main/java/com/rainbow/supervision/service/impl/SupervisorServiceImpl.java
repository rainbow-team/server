package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.dao.FileInfoMapper;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.StrUtil;
import com.rainbow.supervision.controller.domain.SupervisionSupervisorResponse;
import com.rainbow.supervision.dao.SupervisorMapper;
import com.rainbow.supervision.domain.Supervisor;
import com.rainbow.supervision.service.SupervisorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 13260 on 2019/5/11.
 */
@Service("supervisionSupervisorService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SupervisorServiceImpl extends BaseService<Supervisor> implements SupervisorService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SupervisorMapper supervisorMapper;

    @Autowired
    private FileInfoMapper FileInfoMapper;

    @Override
    public int addSupervisor(Supervisor supervisor) {
        return 0;
    }

    @Override
    public int modifySupervisor(Supervisor supervisor) {
        return 0;
    }

    @Override
    public int deleteSupervisorById(String id) {
        return 0;
    }

    @Override
    public ResponseBo getSupervisorList(Page page) {
        return null;
    }

    @Override
    public ResponseBo getSupervisorById(String id) {
        return null;
    }


    //@Override
    //public  ResponseBo saveOrUpdateSupervisionSupervisor(Supervisor supervisor){
    //    if(supervisor !=null){
    //
    //        if(StrUtil.isNullOrEmpty(supervisor.getId())){
    //
    //            supervisor.setId(UUID.randomUUID().toString());
    //            supervisor.setCreateDate(new Date());
    //            supervisor.setModifyDate(new Date());
    //            supervisionSupervisorMapper.insert(supervisor);
    //
    //        }else{
    //
    //            supervisor.setModifyDate(new Date());
    //            supervisionSupervisorMapper.updateByPrimaryKey(supervisor);
    //        }
    //
    //        if(supervisor.getAttachmentList().size()>0){
    //            Map<String,Object> map = new HashMap<>();
    //            map.put("id", supervisor.getId());
    //            map.put("fileIds", supervisor.getAttachmentList());
    //            FileInfoMapper.updateFileInfoByIds(map);
    //        }
    //
    //    }
    //
    //    return ResponseBo.ok();
    //}
    //
    //@Override
    //public ResponseBo getSupervisionSupervisorList(Page page){
    //
    //    PageHelper.startPage(page.getPageNo(), page.getPageSize());
    //    Map<String, Object> map = page.getQueryParameter();
    //    List<SupervisionSupervisorResponse> list = supervisionSupervisorMapper.getSupervisionSupervisorList(map);
    //
    //    PageInfo<SupervisionSupervisorResponse> pageInfo = new PageInfo<SupervisionSupervisorResponse>(list);
    //
    //    PagingEntity<SupervisionSupervisorResponse> result = new PagingEntity<>(pageInfo);
    //
    //    return ResponseBo.ok(result);
    //}

}
