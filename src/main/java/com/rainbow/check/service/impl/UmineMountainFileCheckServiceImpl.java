package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.check.dao.UmineMountainFileCheckMapper;
import com.rainbow.check.dao.UminePlaceFileCheckMapper;
import com.rainbow.check.domain.UmineMountainFileCheck;
import com.rainbow.check.domain.UmineMountainFileCheckExtend;
import com.rainbow.check.domain.UminePlaceFileCheck;
import com.rainbow.check.service.UmineMountainFileCheckService;
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
@Service("uminemoutainfilecheckservice")
public class UmineMountainFileCheckServiceImpl extends BaseService<UmineMountainFileCheck> implements UmineMountainFileCheckService {

    @Autowired
    UmineMountainFileCheckMapper umineMountainFileCheckMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUmineMountainFileCheck(UmineMountainFileCheck umineMountainFileCheck) {
        umineMountainFileCheck.setId(GuidHelper.getGuid());
        fileInfoService.updateFileInfoByIds(umineMountainFileCheck.getAttachmentList(),umineMountainFileCheck.getId());
        return umineMountainFileCheckMapper.insert(umineMountainFileCheck);
    }

    @Override
    public ResponseBo getUmineMountainFileCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainFileCheckExtend> list = umineMountainFileCheckMapper.getUmineMountainFileCheckList(map);

        PageInfo<UmineMountainFileCheckExtend> pageInfo = new PageInfo<UmineMountainFileCheckExtend>(list);

        PagingEntity<UmineMountainFileCheckExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineMountainFileCheckById(String id) {
        UmineMountainFileCheck result = umineMountainFileCheckMapper.getUmineMountainFileCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

}
