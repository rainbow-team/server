package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.GroupExtend;
import com.rainbow.unit.service.EquipDepartService;
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
@Service("equipdepartservice")
public class EquipDepartServiceImpl extends BaseService<EquipDepart> implements EquipDepartService {


    @Autowired
    EquipDepartMapper equipDepartMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addEquipDepart(EquipDepart equipDepart) {
        equipDepart.setId(GuidHelper.getGuid());
        equipDepart.setCreateDate(new Date());
        equipDepart.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(equipDepart.getAttachmentList(),equipDepart.getId());
        return equipDepartMapper.insert(equipDepart);
    }

    @Override
    public int modifyEquipDepart(EquipDepart equipDepart) {
        equipDepart.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(equipDepart.getAttachmentList(),equipDepart.getId());
        return equipDepartMapper.updateByPrimaryKey(equipDepart);
    }

    @Override
    public ResponseBo getEquipDepartList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<EquipDepart> list = equipDepartMapper.getEquipDepartList(map);

        PageInfo<EquipDepart> pageInfo = new PageInfo<EquipDepart>(list);

        PagingEntity<EquipDepart> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public int deleteEquipDepartById(String id) {
        Object result = equipDepartMapper.getEquipDepartRelationCount(id);
        if (result != null) {
            return equipDepartMapper.deleteEquipDepartById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getEquipDepartById(String id) {
        EquipDepart result = equipDepartMapper.getEquipDepartById(id);

        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }
}