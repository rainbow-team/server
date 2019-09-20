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
import com.rainbow.monitor.dao.WitnessMonitorMapper;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.service.WitnessMonitorService;
import com.rainbow.security.dao.FacSecurityMapper;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.extend.FacSecurityExtend;
import com.rainbow.security.service.FacSecurityService;
import com.rainbow.supervision.domain.extend.BreakCheckerExtend;
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
@Service("facsecurityservice")
public class FacSecurityServiceImpl extends BaseService<FacSecurity> implements FacSecurityService {

    @Autowired
    FacSecurityMapper facSecurityMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addFacSecurity(FacSecurity facSecurity) {
        facSecurity.setId(GuidHelper.getGuid());
        facSecurity.setCreateDate(new Date());
        facSecurity.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(facSecurity.getAttachmentList(),facSecurity.getId());
        return facSecurityMapper.insert(facSecurity);
    }

    @Override
    public int modifyFacSecurity(FacSecurity facSecurity) {
        facSecurity.setModifyDate(new Date());
        return facSecurityMapper.updateByPrimaryKey(facSecurity);
    }

    @Override
    public ResponseBo getFacSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacSecurityExtend> list = facSecurityMapper.getFacSecurityList(map);

        PageInfo<FacSecurityExtend> pageInfo = new PageInfo<FacSecurityExtend>(list);

        PagingEntity<FacSecurityExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacSecurityById(String id) {
        FacSecurity result = facSecurityMapper.getFacSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportFacSecurity(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<FacSecurityExtend> list = facSecurityMapper.getFacSecurityList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (FacSecurityExtend facSecurityExtend : list) {
                String[] strs = new String[]{
                        facSecurityExtend.getServiceDepartName(),
                        facSecurityExtend.getFacName(),
                        facSecurityExtend.getFacStatusTypeValue(),
                        facSecurityExtend.getCheckTypeValue(),
                        facSecurityExtend.getContent(),
                        DateUtils.DateToString(facSecurityExtend.getFindDate()),
                        facSecurityExtend.getQuestionTypeValue(),
                        facSecurityExtend.getQuestionNatureValue(),
                        facSecurityExtend.getReformStatusTypeValue(),
                        facSecurityExtend.getSuperviseRequire(),
                        facSecurityExtend.getReformPlan(),
                        DateUtils.DateToString(facSecurityExtend.getReformCompleteDate())
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "营运单位",
                "设施名称",
                "设施状态",
                "检查类型",
                "问题内容",
                "发现时间",
                "问题类别",
                "问题性质",
                "整改状态",
                "监督要求",
                "整改方案",
                "整改完成时间"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核设施安全问题列表", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("核设施安全问题列表", "utf-8") + ".xls");
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