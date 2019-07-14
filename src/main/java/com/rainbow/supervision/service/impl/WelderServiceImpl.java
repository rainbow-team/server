package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.WelderMapper;
import com.rainbow.supervision.domain.Welder;
import com.rainbow.supervision.service.WelderService;
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
@Service("SupervisionWelderService")
public class WelderServiceImpl extends BaseService<Welder> implements WelderService {

    @Autowired
    WelderMapper welderMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addWelder(Welder welder) {
        welder.setId(GuidHelper.getGuid());
        welder.setCreateDate(new Date());
        welder.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(welder.getAttachmentList(),welder.getId());
        return welderMapper.insert(welder);
    }

    @Override
    public int modifyWelder(Welder welder) {
        welder.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(welder.getAttachmentList(),welder.getId());
        return welderMapper.updateByPrimaryKey(welder);
    }

    @Override
    public ResponseBo getWelderList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Welder> list = welderMapper.getWelderList(map);

        PageInfo<Welder> pageInfo = new PageInfo<Welder>(list);

        PagingEntity<Welder> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

}