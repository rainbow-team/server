package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.dao.GroupMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.dao.UmineMapper;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.domain.GroupExtend;
import com.rainbow.unit.service.FacImproveService;
import com.rainbow.unit.service.GroupService;
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
@Service("facimproveservice")
public class FacImproveServiceImpl extends BaseService<FacImprove> implements FacImproveService {


    @Autowired
    FacImproveMapper facImproveMapper;

    @Override
    public int addFacImprove(FacImprove facImprove) {
        facImprove.setId(GuidHelper.getGuid());
        return facImproveMapper.insert(facImprove);
    }

    @Override
    public ResponseBo getFacImproveList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacImprove> list = facImproveMapper.getFacImproveList(map);

        PageInfo<FacImprove> pageInfo = new PageInfo<FacImprove>(list);

        PagingEntity<FacImprove> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
