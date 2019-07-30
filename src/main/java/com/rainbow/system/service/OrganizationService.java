package com.rainbow.system.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.system.domain.Organization;

/**
 * @Author:idealks
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface OrganizationService extends IService<Organization> {

    int addOrganization(Organization organization);

    int modifyOrganization(Organization organization);

    ResponseBo getOrganizationList(Page page);

    ResponseBo getOrganizationById(String id);
}
