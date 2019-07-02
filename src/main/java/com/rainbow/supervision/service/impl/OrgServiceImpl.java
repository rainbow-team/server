package com.rainbow.supervision.service.impl;

import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.supervision.dao.OrgMapper;
import com.rainbow.supervision.dao.RelationOrgNatureMapper;
import com.rainbow.supervision.dao.SupervisionSastindMapper;
import com.rainbow.supervision.domain.Org;
import com.rainbow.supervision.domain.RelationOrgNature;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.service.OrgService;
import com.rainbow.supervision.service.SastindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("orgservice")
public class OrgServiceImpl extends BaseService<Org> implements OrgService {

    @Autowired
    OrgMapper orgMapper;


    @Autowired
    RelationOrgNatureMapper relationOrgNatureMapper;


    @Override
    public int addOrg(Org org) {

        org.setId(GuidHelper.getGuid());
        org.setCreateDate(new Date());
        org.setModifyDate(new Date());
        if (org.getNature() != null) {
            List<SystemConfig> natureList = org.getNature();
            for (SystemConfig systemConfig : natureList) {
                RelationOrgNature relationOrgNature = new RelationOrgNature();
                relationOrgNature.setId(GuidHelper.getGuid());
                relationOrgNature.setOrgId(org.getId());
                relationOrgNature.setNatureId(systemConfig.getId());
                relationOrgNatureMapper.insert(relationOrgNature);
            }
        }
        return orgMapper.insert(org);
    }

    @Override
    public int modifyOrg(Org org) {
        org.setModifyDate(new Date());
        return orgMapper.updateByPrimaryKey(org);
    }

}
