package com.rainbow.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.security.dao.EquipSecurityMapper;
import com.rainbow.security.dao.FacSecurityMapper;
import com.rainbow.security.domain.EquipSecurity;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.service.EquipSecurityService;
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
@Service("equipsecurityservice")
public class EquipSecurityServiceImpl extends BaseService<EquipSecurity> implements EquipSecurityService {

    @Autowired
    EquipSecurityMapper equipSecurityMapper;

    @Override
    public int addEquipSecurity(EquipSecurity activityCheck) {
        activityCheck.setId(GuidHelper.getGuid());
        activityCheck.setCreateDate(new Date());
        activityCheck.setModifyDate(new Date());
        return equipSecurityMapper.insert(activityCheck);
    }

    @Override
    public int modifyEquipSecurity(EquipSecurity activityCheck) {
        activityCheck.setModifyDate(new Date());
        return equipSecurityMapper.updateByPrimaryKey(activityCheck);
    }

    @Override
    public ResponseBo getEquipSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<EquipSecurity> list = equipSecurityMapper.getEquipSecurityList(map);

        PageInfo<EquipSecurity> pageInfo = new PageInfo<EquipSecurity>(list);

        PagingEntity<EquipSecurity> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getEquipSecurityById(String id) {
        EquipSecurity result = equipSecurityMapper.getEquipSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}