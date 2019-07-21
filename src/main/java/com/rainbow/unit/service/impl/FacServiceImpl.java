package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.FacReportMapper;
import com.rainbow.unit.dao.GroupMapper;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.FacExtend;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.service.FacService;
import com.rainbow.unit.service.GroupService;
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
@Service("facservice")
public class FacServiceImpl extends BaseService<Fac> implements FacService {


    @Autowired
    FacMapper facMapper;

    @Autowired
    FacImproveMapper facImproveMapper;

    @Autowired
    FacReportMapper facReportMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addFac(Fac fac) {
        fac.setId(GuidHelper.getGuid());
        fac.setCreateDate(new Date());
        fac.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(fac.getAttachmentList(),fac.getId());
        return facMapper.insert(fac);
    }

    @Override
    public int modifyFac(Fac fac) {
        fac.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(fac.getAttachmentList(),fac.getId());
        return facMapper.updateByPrimaryKey(fac);
    }

    @Override
    public ResponseBo getFacList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Fac> list = facMapper.getFacList(map);

        PageInfo<Fac> pageInfo = new PageInfo<Fac>(list);

        PagingEntity<Fac> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacById(String id) {
        Fac result = facMapper.getFacById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public int deleteFacById(String id) {
        Object result = facMapper.getFacRelationCount(id);
        if (result != null) {
            return facMapper.deleteFacById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getFacListByServiceId(String serviceId) {
        List<Fac> result = facMapper.getFacListByServiceId(serviceId);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}
