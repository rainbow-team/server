package com.rainbow.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.permit.dao.EquipPermitMapper;
import com.rainbow.permit.domain.EquipPermit;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.service.EquipPermitService;
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
@Service("equippermitservice")
public class EquipPermitServiceImpl extends BaseService<EquipPermit> implements EquipPermitService {

    @Autowired
    EquipPermitMapper equipPermitMapper;

    @Override
    public int addEquipPermit(EquipPermit equipPermit) {
        equipPermit.setId(GuidHelper.getGuid());
        equipPermit.setCreateDate(new Date());
        equipPermit.setModifyDate(new Date());
        return equipPermitMapper.insert(equipPermit);
    }

    @Override
    public int modifyEquipPermit(EquipPermit equipPermit) {
        equipPermit.setModifyDate(new Date());
        return equipPermitMapper.updateByPrimaryKey(equipPermit);
    }

    @Override
    public ResponseBo getEquipPermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<EquipPermit> list = equipPermitMapper.getEquipPermitList(map);

        PageInfo<EquipPermit> pageInfo = new PageInfo<EquipPermit>(list);

        PagingEntity<EquipPermit> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getEquipPermitById(String id) {
        EquipPermit result = equipPermitMapper.getEquipPermitById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}