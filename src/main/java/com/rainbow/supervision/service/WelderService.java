package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.Welder;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface WelderService extends IService<Welder> {

    int addWelder(Welder welder);

    int modifyWelder(Welder welder);

    ResponseBo getWelderList(Page page);

    void exportWelderTrain(Page page,HttpServletResponse response);

}
