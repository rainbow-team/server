package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.UminePlaceImproveMapper;
import com.rainbow.unit.dao.UmineplaceMapper;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.ServiceDepart;
import com.rainbow.unit.domain.Umineplace;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.UmineplaceService;
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
@Service("umineplaceservice")
public class UmineplaceServiceImpl extends BaseService<Umineplace> implements UmineplaceService {


    @Autowired
    UmineplaceMapper umineplaceMapper;

    @Autowired
    UminePlaceImproveMapper uminePlaceImproveMapper;

    @Override
    public int addUmineplace(Umineplace umineplace) {
        umineplace.setId(GuidHelper.getGuid());
        umineplace.setCreateDate(new Date());
        umineplace.setModifyDate(new Date());
        return umineplaceMapper.insert(umineplace);
    }

    @Override
    public int modifyUmineplace(Umineplace umineplace) {
        umineplace.setModifyDate(new Date());
        return umineplaceMapper.updateByPrimaryKey(umineplace);
    }

    @Override
    public ResponseBo getUmineplaceList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Umineplace> list = umineplaceMapper.getUmineplaceList(map);

        PageInfo<Umineplace> pageInfo = new PageInfo<Umineplace>(list);

        PagingEntity<Umineplace> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineplaceById(String id) {
        Umineplace result = umineplaceMapper.getUmineplaceById(id);
        return ResponseBo.ok(result);
    }

    @Override
    public int deleteUmineplaceById(String id) {
        Object result = umineplaceMapper.getUmineplaceRelationCount(id);
        if (result != null) {
            return umineplaceMapper.deleteUmineplaceById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getUmineplaceListByUmineId(String umineId) {
        List<Umineplace> result = umineplaceMapper.getUmineplaceListByUmineId(umineId);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}
