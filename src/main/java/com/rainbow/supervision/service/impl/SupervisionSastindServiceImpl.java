package com.rainbow.supervision.service.impl;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionSastindMapper;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.service.SupervisionSastindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("SupervisionSastindService")
public class SupervisionSastindServiceImpl extends BaseService<SupervisionSastind> implements SupervisionSastindService {

    @Autowired
    SupervisionSastindMapper sastindMapper;
    /**
     * 保存国防科工局信息
     * @param
     * @return
     */
    @Override
    public int saveSastind(SupervisionSastind sastind) {
      sastind.setId(GuidHelper.getGuid());
      sastind.setCreateDate(new Date());
      sastind.setModifyDate(new Date());
      return sastindMapper.insert(sastind);
    }

    /**
     * 修改国防科工局信息
     * @param sastind
     * @return
     */
    @Override
    public int modifySastind(SupervisionSastind sastind) {
        sastind.setModifyDate(new Date());
        return sastindMapper.updateByPrimaryKey(sastind);
    }
}
