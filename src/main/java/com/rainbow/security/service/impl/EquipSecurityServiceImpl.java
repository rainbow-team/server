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
import com.rainbow.security.dao.EquipSecurityMapper;
import com.rainbow.security.dao.FacSecurityMapper;
import com.rainbow.security.domain.EquipSecurity;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.extend.EquipSecurityExtend;
import com.rainbow.security.domain.extend.FacSecurityExtend;
import com.rainbow.security.service.EquipSecurityService;
import com.rainbow.security.service.FacSecurityService;
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
@Service("equipsecurityservice")
public class EquipSecurityServiceImpl extends BaseService<EquipSecurity> implements EquipSecurityService {

    @Autowired
    EquipSecurityMapper equipSecurityMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addEquipSecurity(EquipSecurity equipSecurity) {
        equipSecurity.setId(GuidHelper.getGuid());
        equipSecurity.setCreateDate(new Date());
        equipSecurity.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(equipSecurity.getAttachmentList(),equipSecurity.getId());
        return equipSecurityMapper.insert(equipSecurity);
    }

    @Override
    public int modifyEquipSecurity(EquipSecurity equipSecurity) {
        equipSecurity.setModifyDate(new Date());
        return equipSecurityMapper.updateByPrimaryKey(equipSecurity);
    }

    @Override
    public ResponseBo getEquipSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<EquipSecurityExtend> list = equipSecurityMapper.getEquipSecurityList(map);

        PageInfo<EquipSecurityExtend> pageInfo = new PageInfo<EquipSecurityExtend>(list);

        PagingEntity<EquipSecurityExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getEquipSecurityById(String id) {
        EquipSecurity result = equipSecurityMapper.getEquipSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportEquipSecurity(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<EquipSecurityExtend> list = equipSecurityMapper.getEquipSecurityList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (EquipSecurityExtend equipSecurityExtend : list) {
                String[] strs = new String[]{
                        equipSecurityExtend.getEquipDepartName(),
                        equipSecurityExtend.getName(),
                        equipSecurityExtend.getServiceDepartName(),
                        equipSecurityExtend.getFacName(),
                        equipSecurityExtend.getCheckTypeValue(),
                        equipSecurityExtend.getContent(),
                        DateUtils.DateToString(equipSecurityExtend.getFindDate()),
                        equipSecurityExtend.getQuestionTypeValue(),
                        equipSecurityExtend.getReformStatusTypeValue(),
                        equipSecurityExtend.getSuperviseRequire(),
                        equipSecurityExtend.getReformPlan(),
                        DateUtils.DateToString(equipSecurityExtend.getReformCompleteDate())
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "核设备单位",
                "设施名称",
                "核设施营运单位",
                "核设施名称",
                "发现方式",
                "问题内容",
                "发现时间",
                "问题类别",
                "整改状态",
                "监管要求",
                "整改方案",
                "整改完成时间",
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核安全设备安全问题", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("核安全设备安全问题", "utf-8") + ".xls");
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