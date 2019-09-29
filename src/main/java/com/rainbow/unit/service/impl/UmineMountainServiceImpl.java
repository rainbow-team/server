package com.rainbow.unit.service.impl;

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
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.UmineMountainImproveMapper;
import com.rainbow.unit.dao.UmineMountainMapper;
import com.rainbow.unit.domain.*;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.UmineMountainService;
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
@Service("uminemountainservice")
public class UmineMountainServiceImpl extends BaseService<UmineMountain> implements UmineMountainService {


    @Autowired
    UmineMountainMapper umineMountainMapper;

    @Autowired
    UmineMountainImproveMapper umineMountainImproveMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUmineMountain(UmineMountain umineMountain) {
        umineMountain.setId(GuidHelper.getGuid());
        umineMountain.setCreateDate(new Date());
        umineMountain.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(umineMountain.getAttachmentList(),umineMountain.getId());
        return umineMountainMapper.insert(umineMountain);
    }

    @Override
    public int modifyUmineMountain(UmineMountain umineMountain) {
        umineMountain.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(umineMountain.getAttachmentList(),umineMountain.getId());
        return umineMountainMapper.updateByPrimaryKey(umineMountain);
    }

    @Override
    public ResponseBo getUmineMountainList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainExtend> list = umineMountainMapper.getUmineMountainList(map);

        PageInfo<UmineMountainExtend> pageInfo = new PageInfo<UmineMountainExtend>(list);

        PagingEntity<UmineMountainExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineMountainById(String id) {

        UmineMountain result = umineMountainMapper.getUmineMountainById(id);

        return result != null ? ResponseBo.ok(result) : ResponseBo.error("获取失败");
    }

    @Override
    public int deleteUmineMountainById(String id) {
        Object result = umineMountainMapper.getUmineMountainRelationCount(id);
        if (result != null) {
           return umineMountainMapper.deleteUmineMountainById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getUminemountainListByUmineId(String umineId) {
        List<UmineMountain> result = umineMountainMapper.getUminemountainListByUmineId(umineId);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportUmineMountain(Page page, HttpServletResponse response){

        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainExtend> list = umineMountainMapper.getUmineMountainList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<UmineMountainImprove> UmineMountainImproveList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (UmineMountainExtend umineMountainExtend : list) {

                int buildYear = DateUtils.getDateYear(umineMountainExtend.getBuildYear());
                String[] strs = new String[] {
                        umineMountainExtend.getName(),
                        umineMountainExtend.getUmineName(),
                        Integer.toString(buildYear),
                        umineMountainExtend.getStatusValue(),
                        umineMountainExtend.getRecordValue(),
                        umineMountainExtend.getAcceptValue(),
                        umineMountainExtend.getSurvey(),
                        umineMountainExtend.getDangerFirepoint(),
                        umineMountainExtend.getWaterVolumn(),
                        umineMountainExtend.getNote()
                };
                cloumnValues.add(strs);

                // 安技改信息
                Map<String, Object> map1 = new HashMap<>();
                map1.put("umineMountainId", umineMountainExtend.getId());
                List<UmineMountainImprove> list1 = umineMountainImproveMapper.getUmineMountainImproveList(map1);
                if (list1 != null && list1.size() > 0) {
                    UmineMountainImproveList.addAll(list1);
                }
            }

            String[] cloumnNames = new String[] {
                "铀矿山名称","营运单位","建造年代","铀矿山设施状态","井下消防审查备案情况","井下消防验收情况",
                    "井下消防设计简介","井下重点火灾危险源","消防水池容积",
                    "备注"
            };

            HSSFWorkbook wb = new HSSFWorkbook();
            wb = ExportExcel.getHssfWorkBook(wb, "铀矿山信息", cloumnNames, cloumnValues);

            // 安技改信息
            String[] cloumnNames1 = new String[] {  "铀矿山信息", "安技改时间", "安技改内容" };

            cloumnValues = new ArrayList<>();
            if (UmineMountainImproveList.size() > 0) {
                for (UmineMountainImprove umineMountainImprove : UmineMountainImproveList) {

                    String[] strs = new String[] {
                            umineMountainImprove.getUmineMountainName(),
                            DateUtils.DateToString(umineMountainImprove.getImproveDate()),
                            umineMountainImprove.getImproveContent()
                    };
                    cloumnValues.add(strs);
                }
            }

            wb = ExportExcel.getHssfWorkBook(wb, "安技改信息", cloumnNames1, cloumnValues);

            try {
                response.setHeader("content-disposition",
                        "attachment;filename=" + URLEncoder.encode("铀矿山信息", "utf-8") + ".xls");
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
}
