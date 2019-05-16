package com.rainbow.supervision.service;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisionSastind;

import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:10
 * @Description:
 **/
public interface SupervisionSastindService extends IService<SupervisionSastind> {

    int saveSastind(SupervisionSastind sastind);

    int modifySastind(SupervisionSastind sastind);

}
