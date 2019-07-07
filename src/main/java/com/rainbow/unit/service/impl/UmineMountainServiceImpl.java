package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.UmineMountainMapper;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.UmineMountain;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.UmineMountainService;
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
@Service("uminemountainservice")
public class UmineMountainServiceImpl extends BaseService<UmineMountain> implements UmineMountainService {


    @Autowired
    UmineMountainMapper umineMountainMapper;


    @Override
    public int addUmineMountain(UmineMountain umineMountain) {
        umineMountain.setId(GuidHelper.getGuid());
        umineMountain.setCreateDate(new Date());
        umineMountain.setModifyDate(new Date());
        return umineMountainMapper.insert(umineMountain);
    }

    @Override
    public int modifyUmineMountain(UmineMountain umineMountain) {
        umineMountain.setModifyDate(new Date());
        return umineMountainMapper.updateByPrimaryKey(umineMountain);
    }

    @Override
    public ResponseBo getUmineMountainList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountain> list = umineMountainMapper.getUmineMountainList(map);

        PageInfo<UmineMountain> pageInfo = new PageInfo<UmineMountain>(list);

        PagingEntity<UmineMountain> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineMountainById(String id) {
        //return null;
        UmineMountain result= umineMountainMapper.getUmineMountainById(id);
        return ResponseBo.ok(result);
    }
}
