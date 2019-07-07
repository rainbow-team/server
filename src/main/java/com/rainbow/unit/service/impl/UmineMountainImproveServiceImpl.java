package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.unit.dao.UmineMountainImproveMapper;
import com.rainbow.unit.domain.UmineMountainImprove;
import com.rainbow.unit.domain.UminePlaceImprove;
import com.rainbow.unit.service.UmineMountainImproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("uminemountainimproveservice")
public class UmineMountainImproveServiceImpl extends BaseService<UmineMountainImprove> implements UmineMountainImproveService {


    @Autowired
    UmineMountainImproveMapper umineMountainImproveMapper;


    @Override
    public void deleteUmineMountainImprove(List<String> ids) {
        super.batchDelete(ids,"id",UmineMountainImprove.class);
    }

    @Override
    public ResponseBo getUmineMountainImproveList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainImprove> list = umineMountainImproveMapper.getUmineMountainImproveList(map);

        PageInfo<UmineMountainImprove> pageInfo = new PageInfo<UmineMountainImprove>(list);

        PagingEntity<UmineMountainImprove> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

}
