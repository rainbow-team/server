package com.rainbow.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.monitor.dao.WitnessMonitorMapper;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.service.WitnessMonitorService;
import com.rainbow.security.dao.FacSecurityMapper;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.service.FacSecurityService;
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
@Service("facsecurityservice")
public class FacSecurityServiceImpl extends BaseService<FacSecurity> implements FacSecurityService {

    @Autowired
    FacSecurityMapper facSecurityMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addFacSecurity(FacSecurity facSecurity) {
        facSecurity.setId(GuidHelper.getGuid());
        facSecurity.setCreateDate(new Date());
        facSecurity.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(facSecurity.getAttachmentList(),facSecurity.getId());
        return facSecurityMapper.insert(facSecurity);
    }

    @Override
    public int modifyFacSecurity(FacSecurity facSecurity) {
        facSecurity.setModifyDate(new Date());
        return facSecurityMapper.updateByPrimaryKey(facSecurity);
    }

    @Override
    public ResponseBo getFacSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacSecurity> list = facSecurityMapper.getFacSecurityList(map);

        PageInfo<FacSecurity> pageInfo = new PageInfo<FacSecurity>(list);

        PagingEntity<FacSecurity> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacSecurityById(String id) {
        FacSecurity result = facSecurityMapper.getFacSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}