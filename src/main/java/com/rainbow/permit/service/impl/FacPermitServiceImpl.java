package com.rainbow.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.permit.dao.FacPermitMapper;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.service.FacPermitService;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.FacReportMapper;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.service.FacService;
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
@Service("facpermitservice")
public class FacPermitServiceImpl extends BaseService<FacPermit> implements FacPermitService {


    @Autowired
    FacPermitMapper facPermitMapper;

    @Override
    public int addFacPermit(FacPermit facPermit) {
        facPermit.setId(GuidHelper.getGuid());
        facPermit.setCreateDate(new Date());
        facPermit.setModifyDate(new Date());
        return facPermitMapper.insert(facPermit);
    }

    @Override
    public int modifyFacPermit(FacPermit facPermit) {
        facPermit.setModifyDate(new Date());
        return facPermitMapper.updateByPrimaryKey(facPermit);
    }

    @Override
    public ResponseBo getFacPermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacPermit> list = facPermitMapper.getFacPermitList(map);

        PageInfo<FacPermit> pageInfo = new PageInfo<FacPermit>(list);

        PagingEntity<FacPermit> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacPermitById(String id) {
        FacPermit result = facPermitMapper.getFacPermitById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}