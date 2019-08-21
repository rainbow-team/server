package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.dao.FileInfoMapper;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.common.util.StrUtil;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.supervision.controller.domain.SupervisionSupervisorResponse;
import com.rainbow.supervision.dao.SupervisionTrainRecordMapper;
import com.rainbow.supervision.dao.SupervisorMapper;
import com.rainbow.supervision.domain.Supervisor;
import com.rainbow.supervision.domain.extend.SupervisorExtend;
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

    @Autowired
    private SupervisionTrainRecordMapper supervisionTrainRecordMapper;

    @Override
    public int addSupervisor(Supervisor supervisor) {
        supervisor.setId(GuidHelper.getGuid());
        supervisor.setCreateDate(new Date());
        supervisor.setModifyDate(new Date());
        return supervisorMapper.insert(supervisor);
    }

    @Override
    public int modifySupervisor(Supervisor supervisor) {
        supervisor.setModifyDate(new Date());
       return updateNotNull(supervisor);
        //return supervisorMapper.updateByPrimaryKey(supervisor);
    }

    @Override
    public ResponseBo deleteSupervisorById(String id) {
        int result = 0;
        int count = supervisionTrainRecordMapper.getRecordCountBySupervisorId(id);
        if (count == 0) {
            result = super.deleteByKey(id);
        } else {
            return ResponseBo.error("已经被关联，删除失败!");
        }
        return result == 0 ? ResponseBo.error("删除失败") : ResponseBo.ok("删除成功");
    }

    @Override
    public ResponseBo getSupervisorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisorExtend> list = supervisorMapper.getSupervisorList(map);

        PageInfo<SupervisorExtend> pageInfo = new PageInfo<SupervisorExtend>(list);

        PagingEntity<SupervisorExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getSupervisorById(String id) {
        SupervisorExtend result = supervisorMapper.getSupervisorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
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
