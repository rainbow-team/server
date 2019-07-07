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
import com.rainbow.supervision.dao.SupervisionSupervisorMapper;
import com.rainbow.supervision.domain.SupervisionMonitorTrain;
import com.rainbow.supervision.domain.SupervisionSupervisor;
import com.rainbow.supervision.service.SupervisionSupervisorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by 13260 on 2019/5/11.
 */
@Service("supervisionSupervisorService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SupervisionSupervisorServiceImpl extends BaseService<SupervisionSupervisor> implements SupervisionSupervisorService{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SupervisionSupervisorMapper supervisionSupervisorMapper;

    @Autowired
    private FileInfoMapper FileInfoMapper;

    @Override
    public  ResponseBo saveOrUpdateSupervisionSupervisor(SupervisionSupervisor supervisionSupervisor){
        if(supervisionSupervisor!=null){

            if(StrUtil.isNullOrEmpty(supervisionSupervisor.getId())){

                supervisionSupervisor.setId(UUID.randomUUID().toString());
                supervisionSupervisor.setCreateDate(new Date());
                supervisionSupervisor.setModifyDate(new Date());
                supervisionSupervisorMapper.insert(supervisionSupervisor);

            }else{

                supervisionSupervisor.setModifyDate(new Date());
                supervisionSupervisorMapper.updateByPrimaryKey(supervisionSupervisor);
            }

            if(supervisionSupervisor.getAttachmentList().size()>0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",supervisionSupervisor.getId());
                map.put("fileIds",supervisionSupervisor.getAttachmentList());
                FileInfoMapper.updateFileInfoByIds(map);
            }

        }

        return ResponseBo.ok();
    }

    @Override
    public ResponseBo getSupervisionSupervisorList(Page page){

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionSupervisorResponse> list = supervisionSupervisorMapper.getSupervisionSupervisorList(map);

        PageInfo<SupervisionSupervisorResponse> pageInfo = new PageInfo<SupervisionSupervisorResponse>(list);

        PagingEntity<SupervisionSupervisorResponse> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

}
