package com.rainbow.config.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.config.domain.LinkDetail;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.permit.domain.ActivityPermit;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/15 10:15
 * @Description:
 **/
public interface LinkService extends IService<LinkDetail> {
    int addLinkDetail(LinkDetail linkDetail);

    ResponseBo getAllLinkList();
}
