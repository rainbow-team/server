package com.rainbow.config.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.config.domain.LinkDetail;

import java.util.List;

public interface LinkDetailMapper extends MyMapper<LinkDetail> {

    List<LinkDetail> getAllLinkList();
}