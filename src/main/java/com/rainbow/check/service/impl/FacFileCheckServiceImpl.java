package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.check.dao.FacFileCheckMapper;
import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.check.domain.FacFileCheckExtend;
import com.rainbow.check.service.FacFileCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.permit.dao.ActivityPermitMapper;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.service.ActivityPermitService;
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
@Service("facfilecheckservice")
public class FacFileCheckServiceImpl extends BaseService<FacFileCheck> implements FacFileCheckService {

    @Autowired
    FacFileCheckMapper facFileCheckMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addFacFileCheck(FacFileCheck facFileCheck) {
        facFileCheck.setId(GuidHelper.getGuid());
        fileInfoService.updateFileInfoByIds(facFileCheck.getAttachmentList(),facFileCheck.getId());
        return facFileCheckMapper.insert(facFileCheck);
    }

    @Override
    public ResponseBo getFacFileCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacFileCheckExtend> list = facFileCheckMapper.getFacFileCheckList(map);

        PageInfo<FacFileCheckExtend> pageInfo = new PageInfo<FacFileCheckExtend>(list);

        PagingEntity<FacFileCheckExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacFileCheckById(String id) {
        FacFileCheck result = facFileCheckMapper.getFacFileCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}
