package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.EquipFileCheckMapper;
import com.rainbow.check.dao.FacFileCheckMapper;
import com.rainbow.check.domain.EquipFileCheck;
import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.check.service.EquipFileCheckService;
import com.rainbow.check.service.FacFileCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("equipfilecheckservice")
public class EquipFileCheckServiceImpl extends BaseService<EquipFileCheck> implements EquipFileCheckService {

    @Autowired
    EquipFileCheckMapper equipFileCheckMapper;



    @Override
    public int addEquipFileCheck(EquipFileCheck equipFileCheck) {
        equipFileCheck.setId(GuidHelper.getGuid());
        return equipFileCheckMapper.insert(equipFileCheck);
    }

    @Override
    public ResponseBo getEquipFileCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<EquipFileCheck> list = equipFileCheckMapper.getEquipFileCheckList(map);

        PageInfo<EquipFileCheck> pageInfo = new PageInfo<EquipFileCheck>(list);

        PagingEntity<EquipFileCheck> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getEquipFileCheckById(String id) {
        EquipFileCheck result = equipFileCheckMapper.getEquipFileCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}
