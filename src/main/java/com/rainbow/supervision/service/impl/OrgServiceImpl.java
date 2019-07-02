package com.rainbow.supervision.service.impl;

import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.OrgMapper;
import com.rainbow.supervision.dao.SupervisionSastindMapper;
import com.rainbow.supervision.domain.Org;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.service.OrgService;
import com.rainbow.supervision.service.SastindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("orgservice")
public class OrgServiceImpl extends BaseService<Org> implements OrgService {

    @Autowired
    OrgMapper orgMapper;

    @Override
    public int addOrg(Org org) {
        org.setId(GuidHelper.getGuid());
        org.setCreateDate(new Date());
        org.setModifyDate(new Date());
        return orgMapper.insert(org);
    }

    @Override
    public int modifyOrg(Org org) {
        org.setModifyDate(new Date());
        return orgMapper.updateByPrimaryKey(org);
    }

}
