package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.UmineMapper;
import com.rainbow.unit.dao.UmineMountainMapper;
import com.rainbow.unit.dao.UmineplaceMapper;
import com.rainbow.unit.domain.*;
import com.rainbow.unit.service.FacService;
import com.rainbow.unit.service.UmineService;
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
@Service("umineservice")
public class UmineServiceImpl extends BaseService<Umine> implements UmineService {

    @Autowired
    UmineMapper umineMapper;

    @Autowired
    UmineMountainMapper umineMountainMapper;

    @Autowired
    UmineplaceMapper umineplaceMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUmine(Umine umine) {
        umine.setId(GuidHelper.getGuid());
        umine.setCreateDate(new Date());
        umine.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(umine.getAttachmentList(),umine.getId());
        return umineMapper.insert(umine);
    }

    @Override
    public int modifyUmine(Umine umine) {
        umine.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(umine.getAttachmentList(),umine.getId());
        return umineMapper.updateByPrimaryKey(umine);
    }

    @Override
    public ResponseBo getUmineList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Umine> list = umineMapper.getUmineList(map);

        PageInfo<Umine> pageInfo = new PageInfo<Umine>(list);

        PagingEntity<Umine> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineById(String id) {

        UmineExtend result = umineMapper.getUmineById(id);

        if (result != null) {
            int mountainNum = umineMountainMapper.getMountainSumByUmineId(result.getId());

            int placeNum = umineplaceMapper.getUminePlaceSumByUmineId(result.getId());

            result.setUmineMountainNum(mountainNum);
            result.setUmineplaceNum(placeNum);

            return ResponseBo.ok(result);
        }


        return ResponseBo.error("获取失败");
    }

    @Override
    public int deleteUmineById(String id) {
        Object result = umineMapper.getUmineRelationCount(id);
        if (result != null) {
            return umineMapper.deleteUmineById(id);
        }
        return 0;
    }
}
