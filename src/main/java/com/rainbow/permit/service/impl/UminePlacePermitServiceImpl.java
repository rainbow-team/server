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
import com.rainbow.permit.dao.ActivityPermitMapper;
import com.rainbow.permit.dao.UminePlacePermitMapper;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.EquipPermitExtend;
import com.rainbow.permit.domain.UminePlacePermit;
import com.rainbow.permit.domain.UminePlacePermitExtend;
import com.rainbow.permit.service.ActivityPermitService;
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
@Service("umineplacepermitservice")
public class UminePlacePermitServiceImpl extends BaseService<UminePlacePermit> implements UminePlacePermitService {

    @Autowired
    UminePlacePermitMapper uminePlacePermitMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUminePlacePermit(UminePlacePermit uminePlacePermit) {
        uminePlacePermit.setId(GuidHelper.getGuid());
        uminePlacePermit.setCreateDate(new Date());
        uminePlacePermit.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(uminePlacePermit.getAttachmentList(),uminePlacePermit.getId());
        return uminePlacePermitMapper.insert(uminePlacePermit);
    }

    @Override
    public int modifyUminePlacePermit(UminePlacePermit uminePlacePermit) {
        uminePlacePermit.setModifyDate(new Date());
        return uminePlacePermitMapper.updateByPrimaryKey(uminePlacePermit);
    }

    @Override
    public ResponseBo getUminePlacePermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlacePermitExtend> list = uminePlacePermitMapper.getUminePlacePermitList(map);

        PageInfo<UminePlacePermitExtend> pageInfo = new PageInfo<UminePlacePermitExtend>(list);

        PagingEntity<UminePlacePermitExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUminePlacePermitById(String id) {
        UminePlacePermit result = uminePlacePermitMapper.getUminePlacePermitById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportUmineplacePermit(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlacePermitExtend> list = uminePlacePermitMapper.getUminePlacePermitList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (UminePlacePermitExtend uminePlacePermitExtend : list) {
                String[] strs = new String[] {
                        uminePlacePermitExtend.getUmineName(),
                        uminePlacePermitExtend.getUminePlaceName(),
                        uminePlacePermitExtend.getStageValue(),
                        DateUtils.DateToString(uminePlacePermitExtend.getPermitDate()),
                        DateUtils.DateToString(uminePlacePermitExtend.getValidateTime()),
                        uminePlacePermitExtend.getLicence(), uminePlacePermitExtend.getPermitCondition(),
                        uminePlacePermitExtend.getReviewPromise()};
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "营运单位", "铀尾矿(渣)库名称","许可阶段","许可时间",
                "有效期限", "许可文号", "许可条件", "审评承诺"};

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "铀尾矿(渣)库许可信息列表", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("铀尾矿(渣)库许可信息列表", "utf-8") + ".xls");
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