package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.dao.UminePlaceImproveMapper;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.UminePlaceImprove;
import com.rainbow.unit.service.FacImproveService;
import com.rainbow.unit.service.UminePlaceImproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("umineplaceimproveservice")
public class UminePlaceImproveServiceImpl extends BaseService<UminePlaceImprove> implements UminePlaceImproveService {


    @Autowired
    UminePlaceImproveMapper uminePlaceImproveMapper;

    @Override
    public void deleteUminePlaceImprove(List<String> ids) {
        super.batchDelete(ids,"id",UminePlaceImprove.class);
    }

    @Override
    public ResponseBo getUminePlaceImproveList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceImprove> list = uminePlaceImproveMapper.getUminePlaceImproveList(map);

        PageInfo<UminePlaceImprove> pageInfo = new PageInfo<UminePlaceImprove>(list);

        PagingEntity<UminePlaceImprove> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
