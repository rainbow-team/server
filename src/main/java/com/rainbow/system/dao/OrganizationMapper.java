package com.rainbow.system.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.system.domain.Organization;

import java.util.*;

public interface OrganizationMapper extends MyMapper<Organization> {
    List<Organization> getOrganizationList(Map<String, Object> map);
}