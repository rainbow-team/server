package com.rainbow.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.permit.dao.ActivityPermitMapper;
import com.rainbow.permit.dao.UminePlacePermitMapper;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.UminePlacePermit;
import com.rainbow.permit.service.ActivityPermitService;
import com.rainbow.permit.service.UminePlacePermitService;
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
@Service("umineplacepermitservice")
public class UminePlacePermitServiceImpl extends BaseService<UminePlacePermit> implements UminePlacePermitService {

    @Autowired
    UminePlacePermitMapper uminePlacePermitMapper;

    @Override
    public int addUminePlacePermit(UminePlacePermit uminePlacePermit) {
        uminePlacePermit.setId(GuidHelper.getGuid());
        uminePlacePermit.setCreateDate(new Date());
        uminePlacePermit.setModifyDate(new Date());
        return uminePlacePermitMapper.insert(uminePlacePermit);
    }

    @Override
    public int modifyUminePlacePermit(UminePlacePermit uminePlacePermit) {
        uminePlacePermit.setModifyDate(new Date());
        return uminePlacePermitMapper.updateByPrimaryKey(uminePlacePermit);
    }

    @Override
    public ResponseBo getUminePlacePermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlacePermit> list = uminePlacePermitMapper.getUminePlacePermitList(map);

        PageInfo<UminePlacePermit> pageInfo = new PageInfo<UminePlacePermit>(list);

        PagingEntity<UminePlacePermit> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUminePlacePermitById(String id) {
        UminePlacePermit result = uminePlacePermitMapper.getUminePlacePermitById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}