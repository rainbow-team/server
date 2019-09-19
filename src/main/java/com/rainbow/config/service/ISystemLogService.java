package com.rainbow.config.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.config.domain.SystemLog;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by 13260 on 2019/9/19.
 */
public interface ISystemLogService extends IService<SystemLog> {

    ResponseBo getSystemLogList( Page page);
}
