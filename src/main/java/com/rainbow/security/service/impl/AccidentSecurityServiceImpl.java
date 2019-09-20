package com.rainbow.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.security.dao.AccidentSecurityMapper;
import com.rainbow.security.dao.UminePlaceSecurityMapper;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.domain.extend.AccidentSecurityExtend;
import com.rainbow.security.domain.extend.UminePlaceSecurityExtend;
import com.rainbow.security.service.AccidentSecurityService;
import com.rainbow.security.service.UminePlaceSecurityService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("accidentsecurityservice")
public class AccidentSecurityServiceImpl extends BaseService<AccidentSecurity> implements AccidentSecurityService {

    @Autowired
    AccidentSecurityMapper accidentSecurityMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addAccidentSecurity(AccidentSecurity accidentSecurity) {
        accidentSecurity.setId(GuidHelper.getGuid());
        accidentSecurity.setCreateDate(new Date());
        accidentSecurity.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(accidentSecurity.getAttachmentList(), accidentSecurity.getId());
        return accidentSecurityMapper.insert(accidentSecurity);
    }

    @Override
    public int modifyAccidentSecurity(AccidentSecurity accidentSecurity) {
        accidentSecurity.setModifyDate(new Date());
        return accidentSecurityMapper.updateByPrimaryKey(accidentSecurity);
    }

    @Override
    public ResponseBo getAccidentSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<AccidentSecurityExtend> list = accidentSecurityMapper.getAccidentSecurityList(map);

        PageInfo<AccidentSecurityExtend> pageInfo = new PageInfo<AccidentSecurityExtend>(list);

        PagingEntity<AccidentSecurityExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getAccidentSecurityById(String id) {
        AccidentSecurity result = accidentSecurityMapper.getAccidentSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportAccidentSecurity(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<AccidentSecurityExtend> list = accidentSecurityMapper.getAccidentSecurityList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (AccidentSecurityExtend accidentSecurityExtend : list) {
                String departName = "";
                String name = "";
                String status = "";

                if (accidentSecurityExtend.getServiceDepartName() != null) {
                    departName += accidentSecurityExtend.getServiceDepartName();
                }
                if (accidentSecurityExtend.getUmineName() != null) {
                    departName += accidentSecurityExtend.getUmineName();
                }

                if (accidentSecurityExtend.getFacName() != null) {
                    name += accidentSecurityExtend.getFacName();
                }
                if (accidentSecurityExtend.getUminePlaceName() != null) {
                    name += accidentSecurityExtend.getUminePlaceName();
                }

                if (accidentSecurityExtend.getFacStatusValue() != null) {
                    status += accidentSecurityExtend.getFacStatusValue();
                }
                if (accidentSecurityExtend.getUminePlaceStatusValue() != null) {
                    status += accidentSecurityExtend.getUminePlaceStatusValue();
                }

                String[] strs = new String[]{
                        departName,
                        name,
                        status,
                        accidentSecurityExtend.getName(),
                        DateUtils.DateToString(accidentSecurityExtend.getOccurDate()),
                        accidentSecurityExtend.getProcess(),
                        accidentSecurityExtend.getConsequence(),
                        accidentSecurityExtend.getReason(),
                        accidentSecurityExtend.getTypeValue(),
                        accidentSecurityExtend.getNatureValue(),
                        accidentSecurityExtend.getMeasure(),
                        accidentSecurityExtend.getFeedback()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "营运单位",
                "设施名称",
                "设施状态",
                "事故事件名称",
                "事故事件发生时间",
                "事故事件过程",
                "事故事件后果",
                "原因分析",
                "事故事件类别",
                "事故事件性质",
                "处理措施",
                "经验反馈",
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "事故事件列表", cloumnNames, cloumnValues);


        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("事故事件列表", "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            out.write(xlsBytes);
            out.close();
        } catch (Exception e) {

        }
    }
}