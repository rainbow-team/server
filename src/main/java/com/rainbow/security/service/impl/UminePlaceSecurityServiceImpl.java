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
import com.rainbow.security.dao.FacSecurityMapper;
import com.rainbow.security.dao.UminePlaceSecurityMapper;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.domain.extend.EquipSecurityExtend;
import com.rainbow.security.domain.extend.UminePlaceSecurityExtend;
import com.rainbow.security.service.FacSecurityService;
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
@Service("umineplacesecurityservice")
public class UminePlaceSecurityServiceImpl extends BaseService<UminePlaceSecurity> implements UminePlaceSecurityService {

    @Autowired
    UminePlaceSecurityMapper uminePlaceSecurityMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUminePlaceSecurity(UminePlaceSecurity uminePlaceSecurity) {
        uminePlaceSecurity.setId(GuidHelper.getGuid());
        uminePlaceSecurity.setCreateDate(new Date());
        uminePlaceSecurity.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(uminePlaceSecurity.getAttachmentList(),uminePlaceSecurity.getId());
        return uminePlaceSecurityMapper.insert(uminePlaceSecurity);
    }

    @Override
    public int modifyUminePlaceSecurity(UminePlaceSecurity uminePlaceSecurity) {
        uminePlaceSecurity.setModifyDate(new Date());
        return uminePlaceSecurityMapper.updateByPrimaryKey(uminePlaceSecurity);
    }

    @Override
    public ResponseBo getUminePlaceSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceSecurityExtend> list = uminePlaceSecurityMapper.getUminePlaceSecurityList(map);

        PageInfo<UminePlaceSecurityExtend> pageInfo = new PageInfo<UminePlaceSecurityExtend>(list);

        PagingEntity<UminePlaceSecurityExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUminePlaceSecurityById(String id) {
        UminePlaceSecurity result = uminePlaceSecurityMapper.getUminePlaceSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportUmineplaceSecurity(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceSecurityExtend> list = uminePlaceSecurityMapper.getUminePlaceSecurityList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (UminePlaceSecurityExtend uminePlaceSecurityExtend : list) {
                String[] strs = new String[]{
                        uminePlaceSecurityExtend.getUmineName(),
                        uminePlaceSecurityExtend.getUminePlaceName(),
                        uminePlaceSecurityExtend.getUminePlaceStatusTypeValue(),
                        uminePlaceSecurityExtend.getCheckTypeValue(),
                        uminePlaceSecurityExtend.getContent(),
                        DateUtils.DateToString(uminePlaceSecurityExtend.getFindDate()),
                        uminePlaceSecurityExtend.getQuestionTypeValue(),
                        uminePlaceSecurityExtend.getQuestionNatureValue(),
                        uminePlaceSecurityExtend.getReformStatusTypeValue(),
                        uminePlaceSecurityExtend.getSuperviseRequire(),
                        uminePlaceSecurityExtend.getReformPlan(),
                        DateUtils.DateToString(uminePlaceSecurityExtend.getReformCompleteDate())
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
                "整改完成时间",
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "铀尾矿(渣)库安全问题", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("铀尾矿(渣)库安全问题", "utf-8") + ".xls");
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