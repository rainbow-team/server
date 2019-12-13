package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.ExpertMapper;
import com.rainbow.supervision.domain.Expert;
import com.rainbow.supervision.domain.SupervisionProduceTrain;
import com.rainbow.supervision.domain.extend.ExpertExtend;
import com.rainbow.supervision.service.ExpertService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

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

        List<ExpertExtend> list = expertMapper.getExpertList(map);

        PageInfo<ExpertExtend> pageInfo = new PageInfo<ExpertExtend>(list);

        PagingEntity<ExpertExtend> result = new PagingEntity<>(pageInfo);

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

    @Override
    public void exportExpert(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<ExpertExtend> list = expertMapper.getExpertList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (ExpertExtend expertExtend : list) {
                String[] strs = new String[]{
                        expertExtend.getName(),
                        expertExtend.getIdentity(),
                        expertExtend.getSex()==0?"男":"女",
                        expertExtend.getMajor(),
                        expertExtend.getTitleValue(),
                        expertExtend.getAge().toString(),
                        expertExtend.getContact(),
                        expertExtend.getOrg(),
                        expertExtend.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "姓名",
                "身份证号",
                "性别",
                "专业",
                "职称",
                "年龄",
                "联系方式",
                "所属单位",
                "备注"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核安全专家", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("核安全专家", "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            out.write(xlsBytes);
            out.close();
        }catch (Exception e){

        }
    }
}
