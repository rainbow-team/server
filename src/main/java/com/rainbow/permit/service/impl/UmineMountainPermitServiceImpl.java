package com.rainbow.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.permit.dao.UmineMountainPermitMapper;
import com.rainbow.permit.dao.UminePlacePermitMapper;
import com.rainbow.permit.domain.UmineMountainPermit;
import com.rainbow.permit.domain.UminePlacePermit;
import com.rainbow.permit.service.UmineMountainPermitService;
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
@Service("uminemountainpermitservice")
public class UmineMountainPermitServiceImpl extends BaseService<UmineMountainPermit> implements UmineMountainPermitService {


    @Autowired
    UmineMountainPermitMapper umineMountainPermitMapper;


    @Override
    public int addUmineMountainPermit(UmineMountainPermit umineMountainPermit) {
        umineMountainPermit.setId(GuidHelper.getGuid());
        umineMountainPermit.setRecordAttachId(GuidHelper.getGuid());
        umineMountainPermit.setAcceptAttachId(GuidHelper.getGuid());
        umineMountainPermit.setCreateDate(new Date());
        umineMountainPermit.setModifyDate(new Date());
        return umineMountainPermitMapper.insert(umineMountainPermit);
    }

    @Override
    public int modifyUmineMountainPermit(UmineMountainPermit umineMountainPermit) {
        umineMountainPermit.setModifyDate(new Date());
        return umineMountainPermitMapper.updateByPrimaryKey(umineMountainPermit);
    }

    @Override
    public ResponseBo getUmineMountainPermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainPermit> list = umineMountainPermitMapper.getUmineMountainPermitList(map);

        PageInfo<UmineMountainPermit> pageInfo = new PageInfo<UmineMountainPermit>(list);

        PagingEntity<UmineMountainPermit> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineMountainPermitById(String id) {
        UmineMountainPermit result = umineMountainPermitMapper.getUmineMountainPermitById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}