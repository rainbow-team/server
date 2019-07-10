package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.EquipCheckMapper;
import com.rainbow.check.dao.FacCheckMapper;
import com.rainbow.check.domain.EquipCheck;
import com.rainbow.check.domain.FacCheck;
import com.rainbow.check.service.EquipCheckService;
import com.rainbow.check.service.FacCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
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
@Service("equipcheckservice")
public class EquipCheckServiceImpl extends BaseService<EquipCheck> implements EquipCheckService {

    @Autowired
    EquipCheckMapper equipCheckMapper;

    @Override
    public int addEquipCheck(EquipCheck equipCheck) {
        equipCheck.setId(GuidHelper.getGuid());
        equipCheck.setCreateDate(new Date());
        equipCheck.setModifyDate(new Date());
        return equipCheckMapper.insert(equipCheck);
    }

    @Override
    public int modifyEquipCheck(EquipCheck equipCheck) {
        equipCheck.setModifyDate(new Date());
        return equipCheckMapper.updateByPrimaryKey(equipCheck);
    }

    @Override
    public int deleteEquipCheckById(String id) {
        Object result = equipCheckMapper.getEquipCheckRelationCount(id);
        if (result != null) {
            return equipCheckMapper.deleteEquipCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getEquipCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<EquipCheck> list = equipCheckMapper.getEquipCheckList(map);

        PageInfo<EquipCheck> pageInfo = new PageInfo<EquipCheck>(list);

        PagingEntity<EquipCheck> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getEquipCheckById(String id) {
        EquipCheck result = equipCheckMapper.getEquipCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

}