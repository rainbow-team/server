package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.UmineMountainCheckMapper;
import com.rainbow.check.dao.UmineMountainFileCheckMapper;
import com.rainbow.check.dao.UminePlaceCheckMapper;
import com.rainbow.check.domain.*;
import com.rainbow.check.service.UmineMountainCheckService;
import com.rainbow.check.service.UminePlaceCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
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
@Service("uminemountaincheckservice")
public class UmineMountainCheckServiceImpl extends BaseService<UmineMountainCheck> implements UmineMountainCheckService {

    @Autowired
    UmineMountainCheckMapper umineMountainCheckMapper;

    @Autowired
    UmineMountainFileCheckMapper umineMountainFileCheckMapper;

    @Override
    public int addUmineMountainCheck(UmineMountainCheck umineMountainCheck) {
        umineMountainCheck.setId(GuidHelper.getGuid());
        umineMountainCheck.setCreateDate(new Date());
        umineMountainCheck.setModifyDate(new Date());
        return umineMountainCheckMapper.insert(umineMountainCheck);
    }

    @Override
    public int modifyUmineMountainCheck(UmineMountainCheck umineMountainCheck) {
        umineMountainCheck.setModifyDate(new Date());
        return umineMountainCheckMapper.updateByPrimaryKey(umineMountainCheck);
    }

    @Override
    public int deleteUmineMountainCheckById(String id) {
        Object result = umineMountainCheckMapper.getUmineMountainCheckRelationCount(id);
        if (result != null) {
            return umineMountainCheckMapper.deleteUmineMountainCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getUmineMountainCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainCheckExtend> list = umineMountainCheckMapper.getUmineMountainCheckList(map);

        PageInfo<UmineMountainCheckExtend> pageInfo = new PageInfo<UmineMountainCheckExtend>(list);

        PagingEntity<UmineMountainCheckExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineMountainCheckById(String id) {
        UmineMountainCheck result = umineMountainCheckMapper.getUmineMountainCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportUminemountainCheck(Page page, HttpServletResponse response) {

        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainCheckExtend> list = umineMountainCheckMapper.getUmineMountainCheckList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<UmineMountainFileCheckExtend> umineMountainFileCheckExtendList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (UmineMountainCheckExtend umineMountainCheckExtend : list) {

                String[] strs = new String[]{
                        umineMountainCheckExtend.getId(),
                        umineMountainCheckExtend.getUmineName(),
                        umineMountainCheckExtend.getUmineMountainName(),
                        umineMountainCheckExtend.getContent(),
                        DateUtils.DateToString(umineMountainCheckExtend.getCheckDate())
                };
                cloumnValues.add(strs);

                //审评信息
                Map<String,Object> map1  = new HashMap<>();
                map1.put("uminemountainId",umineMountainCheckExtend.getId());
                List<UmineMountainFileCheckExtend> list1 = umineMountainFileCheckMapper.getUmineMountainFileCheckList(map1);
                if(list1!=null&&list1.size()>0){
                    umineMountainFileCheckExtendList.addAll(list1);
                }
            }
        }

        String[] cloumnNames = new String[]{
                "主编号",
                "营运单位",
                "铀矿山名称",
                "审查内容",
                "审查时间"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "铀矿山审评信息列表", cloumnNames, cloumnValues);


        //培审评文件信息
        String[] cloumnNames1 = new String[]{
                "主编号",
                "文件类型",
                "文件文号",
                "文件时间"
        };

        cloumnValues = new ArrayList<>();
        if(umineMountainFileCheckExtendList.size()>0){
            for (UmineMountainFileCheckExtend umineMountainFileCheckExtend : umineMountainFileCheckExtendList) {
                String[] strs = new String[]{
                        umineMountainFileCheckExtend.getCheckUmineMountainId(),
                        umineMountainFileCheckExtend.getUmineMountainCheckFileTypeValue(),
                        umineMountainFileCheckExtend.getFileNo(),
                        DateUtils.DateToString(umineMountainFileCheckExtend.getFileDate())
                };
                cloumnValues.add(strs);
            }
        }

        wb = ExportExcel.getHssfWorkBook(wb, "审评文件列表", cloumnNames1, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("铀矿山审评信息列表", "utf-8") + ".xls");
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