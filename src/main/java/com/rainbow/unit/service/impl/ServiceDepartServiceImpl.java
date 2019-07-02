package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.ServiceDepart;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.ServiceDepartService;
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
@Service("servicedepartservice")
public class ServiceDepartServiceImpl extends BaseService<ServiceDepart> implements ServiceDepartService {


    @Autowired
    ServiceDepartMapper serviceDepartMapper;


    @Override
    public int addServiceDepart(ServiceDepart serviceDepart) {
        serviceDepart.setId(GuidHelper.getGuid());
        serviceDepart.setCreateDate(new Date());
        serviceDepart.setModifyDate(new Date());
        return serviceDepartMapper.insert(serviceDepart);
    }

    @Override
    public int modifyServiceDepart(ServiceDepart serviceDepart) {
        serviceDepart.setModifyDate(new Date());
        return serviceDepartMapper.updateByPrimaryKey(serviceDepart);
    }

    @Override
    public ResponseBo getServiceDepartList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ServiceDepart> list = serviceDepartMapper.getUmineList(map);

        PageInfo<ServiceDepart> pageInfo = new PageInfo<ServiceDepart>(list);

        PagingEntity<ServiceDepart> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
