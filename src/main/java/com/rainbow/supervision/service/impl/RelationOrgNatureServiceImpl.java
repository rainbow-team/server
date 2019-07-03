package com.rainbow.supervision.service.impl;

import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.supervision.dao.OrgMapper;
import com.rainbow.supervision.dao.RelationOrgNatureMapper;
import com.rainbow.supervision.domain.Org;
import com.rainbow.supervision.domain.RelationOrgNature;
import com.rainbow.supervision.service.OrgService;
import com.rainbow.supervision.service.RelationOrgNatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("RelationOrgNatureService")
public class RelationOrgNatureServiceImpl extends BaseService<RelationOrgNature> implements RelationOrgNatureService {
}
