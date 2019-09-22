package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.check.dao.ActivityFileCheckMapper;
import com.rainbow.check.dao.UminePlaceFileCheckMapper;
import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.check.domain.UminePlaceFileCheck;
import com.rainbow.check.service.ActivityFileCheckService;
import com.rainbow.check.service.UminePlaceFileCheckService;
import com.rainbow.common.annotation.SystemLog;
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
@Service("umineplacefilecheckservice")
public class UminePlaceFileCheckServiceImpl extends BaseService<UminePlaceFileCheck> implements UminePlaceFileCheckService {

    @Autowired
    UminePlaceFileCheckMapper uminePlaceFileCheckMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUminePlaceFileCheck(UminePlaceFileCheck uminePlaceFileCheck) {
        uminePlaceFileCheck.setId(GuidHelper.getGuid());
        fileInfoService.updateFileInfoByIds(uminePlaceFileCheck.getAttachmentList(),uminePlaceFileCheck.getId());
        return uminePlaceFileCheckMapper.insert(uminePlaceFileCheck);
    }

    @Override
    public ResponseBo getUminePlaceFileCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceFileCheck> list = uminePlaceFileCheckMapper.getUminePlaceFileCheckList(map);

        PageInfo<UminePlaceFileCheck> pageInfo = new PageInfo<UminePlaceFileCheck>(list);

        PagingEntity<UminePlaceFileCheck> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUminePlaceFileCheckById(String id) {
        UminePlaceFileCheck result = uminePlaceFileCheckMapper.getUminePlaceFileCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

}
