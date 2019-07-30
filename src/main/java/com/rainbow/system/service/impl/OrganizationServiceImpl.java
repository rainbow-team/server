package com.rainbow.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.system.dao.OrganizationMapper;
import com.rainbow.system.domain.Organization;
import com.rainbow.system.service.OrganizationService;
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
@Service("OrganizationService")
public class OrganizationServiceImpl extends BaseService<Organization> implements OrganizationService {

    @Autowired
    OrganizationMapper mapper;

    public int addOrganization(Organization organization) {
        organization.setId(GuidHelper.getGuid());
        return mapper.insert(organization);
    }

    public int modifyOrganization(Organization organization) {
        return mapper.updateByPrimaryKey(organization);
    }

    public ResponseBo getOrganizationList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Organization> list = mapper.getOrganizationList(map);

        PageInfo<Organization> pageInfo = new PageInfo<Organization>(list);

        PagingEntity<Organization> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    public ResponseBo getOrganizationById(String id) {
        Organization result = mapper.selectByPrimaryKey(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}