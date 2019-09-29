package com.rainbow.permit.service.impl;

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
import com.rainbow.permit.dao.UmineMountainPermitMapper;
import com.rainbow.permit.dao.UminePlacePermitMapper;
import com.rainbow.permit.domain.EquipPermitExtend;
import com.rainbow.permit.domain.UmineMountainPermit;
import com.rainbow.permit.domain.UmineMountainPermitExtend;
import com.rainbow.permit.domain.UminePlacePermit;
import com.rainbow.permit.service.UmineMountainPermitService;
import com.rainbow.permit.service.UminePlacePermitService;
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
@Service("uminemountainpermitservice")
public class UmineMountainPermitServiceImpl extends BaseService<UmineMountainPermit> implements UmineMountainPermitService {


    @Autowired
    UmineMountainPermitMapper umineMountainPermitMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUmineMountainPermit(UmineMountainPermit umineMountainPermit) {
        umineMountainPermit.setId(GuidHelper.getGuid());
        umineMountainPermit.setRecordAttachId(GuidHelper.getGuid());
        umineMountainPermit.setAcceptAttachId(GuidHelper.getGuid());
        umineMountainPermit.setCreateDate(new Date());
        umineMountainPermit.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(umineMountainPermit.getBackupAttachmentList(),umineMountainPermit.getRecordAttachId());
        fileInfoService.updateFileInfoByIds(umineMountainPermit.getAcceptpAttachmentList(),umineMountainPermit.getAcceptAttachId());
        return umineMountainPermitMapper.insert(umineMountainPermit);
    }

    @Override
    public int modifyUmineMountainPermit(UmineMountainPermit umineMountainPermit) {
        umineMountainPermit.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(umineMountainPermit.getBackupAttachmentList(),umineMountainPermit.getRecordAttachId());
        fileInfoService.updateFileInfoByIds(umineMountainPermit.getAcceptpAttachmentList(),umineMountainPermit.getAcceptAttachId());
        return umineMountainPermitMapper.updateByPrimaryKey(umineMountainPermit);
    }

    @Override
    public ResponseBo getUmineMountainPermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainPermitExtend> list = umineMountainPermitMapper.getUmineMountainPermitList(map);

        PageInfo<UmineMountainPermitExtend> pageInfo = new PageInfo<UmineMountainPermitExtend>(list);

        PagingEntity<UmineMountainPermitExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineMountainPermitById(String id) {
        UmineMountainPermit result = umineMountainPermitMapper.getUmineMountainPermitById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportUminemountainPermit(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainPermitExtend> list = umineMountainPermitMapper.getUmineMountainPermitList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (UmineMountainPermitExtend umineMountainPermitExtend : list) {
                String[] strs = new String[] {
                        umineMountainPermitExtend.getUmineName(),
                        umineMountainPermitExtend.getUmineMountainName(),
                        DateUtils.DateToString(umineMountainPermitExtend.getRecordDate()),
                        umineMountainPermitExtend.getRecordNumber(),
                        umineMountainPermitExtend.getRecordCondition(),
                        umineMountainPermitExtend.getReviewPromise(),
                        DateUtils.DateToString(umineMountainPermitExtend.getAcceptDate()),
                        umineMountainPermitExtend.getAcceptNumber(),
                        umineMountainPermitExtend.getAcceptConclusion()};
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "营运单位", "铀矿山名称","备案时间","备案文号",
                "主要备案条件", "审评承诺", "验收时间", "验收文号","主要验收结论"};


        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "铀矿山井下消防许可信息列表", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("铀矿山井下消防许可信息列表", "utf-8") + ".xls");
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