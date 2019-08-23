package com.rainbow.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.security.dao.AccidentSecurityMapper;
import com.rainbow.security.dao.UminePlaceSecurityMapper;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.service.AccidentSecurityService;
import com.rainbow.security.service.UminePlaceSecurityService;
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
@Service("accidentsecurityservice")
public class AccidentSecurityServiceImpl extends BaseService<AccidentSecurity> implements AccidentSecurityService {

    @Autowired
    AccidentSecurityMapper accidentSecurityMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addAccidentSecurity(AccidentSecurity accidentSecurity) {
        accidentSecurity.setId(GuidHelper.getGuid());
        accidentSecurity.setCreateDate(new Date());
        accidentSecurity.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(accidentSecurity.getAttachmentList(),accidentSecurity.getId());
        return accidentSecurityMapper.insert(accidentSecurity);
    }

    @Override
    public int modifyAccidentSecurity(AccidentSecurity accidentSecurity) {
        accidentSecurity.setModifyDate(new Date());
        return accidentSecurityMapper.updateByPrimaryKey(accidentSecurity);
    }

    @Override
    public ResponseBo getAccidentSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<AccidentSecurity> list = accidentSecurityMapper.getAccidentSecurityList(map);

        PageInfo<AccidentSecurity> pageInfo = new PageInfo<AccidentSecurity>(list);

        PagingEntity<AccidentSecurity> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getAccidentSecurityById(String id) {
        AccidentSecurity result = accidentSecurityMapper.getAccidentSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}