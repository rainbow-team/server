package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.BreakCheckerMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.service.BreakCheckerService;
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
@Service("breakCheckerService")
public class BreakCheckServiceImpl extends BaseService<BreakChecker> implements BreakCheckerService {

    @Autowired
    BreakCheckerMapper breakCheckerMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addBreakChecker(BreakChecker breakChecker) {
        breakChecker.setId(GuidHelper.getGuid());
        breakChecker.setCreateDate(new Date());
        breakChecker.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(breakChecker.getAttachmentList(),breakChecker.getId());
        return breakCheckerMapper.insert(breakChecker);
    }

    @Override
    public int modifyBreakChecker(BreakChecker breakChecker) {
        breakChecker.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(breakChecker.getAttachmentList(),breakChecker.getId());
        return breakCheckerMapper.updateByPrimaryKey(breakChecker);
    }

    @Override
    public ResponseBo getBreakCheckerList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<BreakChecker> list = breakCheckerMapper.getBreakCheckerList(map);

        PageInfo<BreakChecker> pageInfo = new PageInfo<BreakChecker>(list);

        PagingEntity<BreakChecker> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getBreakCheckerById(String id) {

        BreakChecker result = breakCheckerMapper.getBreakCheckerById(id);

        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败，请重试");
    }
}
