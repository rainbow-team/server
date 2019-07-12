package com.rainbow.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.security.dao.FacSecurityMapper;
import com.rainbow.security.dao.UminePlaceSecurityMapper;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.service.FacSecurityService;
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
@Service("umineplacesecurityservice")
public class UminePlaceSecurityServiceImpl extends BaseService<UminePlaceSecurity> implements UminePlaceSecurityService {

    @Autowired
    UminePlaceSecurityMapper uminePlaceSecurityMapper;

    @Override
    public int addUminePlaceSecurity(UminePlaceSecurity activityCheck) {
        activityCheck.setId(GuidHelper.getGuid());
        activityCheck.setCreateDate(new Date());
        activityCheck.setModifyDate(new Date());
        return uminePlaceSecurityMapper.insert(activityCheck);
    }

    @Override
    public int modifyUminePlaceSecurity(UminePlaceSecurity activityCheck) {
        activityCheck.setModifyDate(new Date());
        return uminePlaceSecurityMapper.updateByPrimaryKey(activityCheck);
    }

    @Override
    public ResponseBo getUminePlaceSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceSecurity> list = uminePlaceSecurityMapper.getUminePlaceSecurityList(map);

        PageInfo<UminePlaceSecurity> pageInfo = new PageInfo<UminePlaceSecurity>(list);

        PagingEntity<UminePlaceSecurity> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUminePlaceSecurityById(String id) {
        UminePlaceSecurity result = uminePlaceSecurityMapper.getUminePlaceSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}