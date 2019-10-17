package com.rainbow.config.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.config.dao.SystemLogMapper;
import com.rainbow.config.domain.SystemLog;
import com.rainbow.config.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/9/19.
 */
@Service("ISystemLogService")
public class SystemLogServiceImpl extends BaseService<SystemLog> implements ISystemLogService{

    @Autowired
    SystemLogMapper systemLogMapper;

    @Override
    public ResponseBo getSystemLogList(Page page){

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SystemLog> list = systemLogMapper.getSystemLogList(map);

        PageInfo<SystemLog> pageInfo = new PageInfo<SystemLog>(list);

        PagingEntity<SystemLog> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);

    }

    @Override
    public ResponseBo deleteSystemLog(){

        systemLogMapper.deleteSystemLog();

        return ResponseBo.ok();
    }
}
