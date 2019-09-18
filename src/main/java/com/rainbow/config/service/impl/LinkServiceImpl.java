package com.rainbow.config.service.impl;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.config.dao.LinkDetailMapper;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.config.domain.LinkDetail;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.config.service.LinkService;
import com.rainbow.config.service.SystemConfigService;
import com.rainbow.permit.dao.ActivityPermitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/15 10:17
 * @Description:
 **/

@Service("LinkService")
public class LinkServiceImpl extends BaseService<LinkDetail> implements LinkService {

    @Autowired
    LinkDetailMapper linkDetailMapper;

    @Override
    public int addLinkDetail(LinkDetail linkDetail) {
        linkDetail.setId(GuidHelper.getGuid());
        return linkDetailMapper.insert(linkDetail);
    }
}

