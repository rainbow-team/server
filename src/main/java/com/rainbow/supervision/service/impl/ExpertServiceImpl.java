package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.ExpertMapper;
import com.rainbow.supervision.domain.Expert;
import com.rainbow.supervision.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("SupervisionExpertService")
public class ExpertServiceImpl extends BaseService<Expert> implements ExpertService {

    @Autowired
    ExpertMapper expertMapper;

    @Override
    public int addExpert(Expert expert) {
        expert.setId(GuidHelper.getGuid());
        expert.setCreateDate(new Date());
        expert.setModifyDate(new Date());
        return expertMapper.insert(expert);
    }

    @Override
    public int modifyExpert(Expert expert) {
        expert.setModifyDate(new Date());
        return expertMapper.updateByPrimaryKey(expert);
    }

    @Override
    public ResponseBo getExpertList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();

        Object startAge = map.get("startAge");
        if (startAge != null) {
            Integer sAge = Integer.parseInt(startAge.toString());
            Date date=new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR,-sAge);
            date=calendar.getTime();
            map.put("end_date",date);
        }

        Object endAge = map.get("endAge");
        if (endAge != null) {
            Integer eAge = Integer.parseInt(endAge.toString())+1;
            Date date=new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR,-eAge);
            date=calendar.getTime();
            map.put("start_date",date);
        }

        List<Expert> list = expertMapper.getExpertList(map);

        PageInfo<Expert> pageInfo = new PageInfo<Expert>(list);

        PagingEntity<Expert> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getExpertById(String id){

        Expert result =  expertMapper.getExpertById(id);

        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败，请重试");

        //创建人
        //String name = userMapper.getUserNameById(result.getCreatorId());
        //result.setCreatorName(name);

    }
}
