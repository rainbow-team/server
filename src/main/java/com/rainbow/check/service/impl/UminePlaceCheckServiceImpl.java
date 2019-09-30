package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.ActivityCheckMapper;
import com.rainbow.check.dao.UminePlaceCheckMapper;
import com.rainbow.check.dao.UminePlaceFileCheckMapper;
import com.rainbow.check.domain.*;
import com.rainbow.check.service.ActivityCheckService;
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
@Service("umineplacecheckservice")
public class UminePlaceCheckServiceImpl extends BaseService<UminePlaceCheck> implements UminePlaceCheckService {

    @Autowired
    UminePlaceCheckMapper uminePlaceCheckMapper;

    @Autowired
    UminePlaceFileCheckMapper uminePlaceFileCheckMapper;


    @Override
    public int addUminePlaceCheck(UminePlaceCheck uminePlaceCheck) {
        uminePlaceCheck.setId(GuidHelper.getGuid());
        uminePlaceCheck.setCreateDate(new Date());
        uminePlaceCheck.setModifyDate(new Date());
        return uminePlaceCheckMapper.insert(uminePlaceCheck);
    }

    @Override
    public int modifyUminePlaceCheck(UminePlaceCheck uminePlaceCheck) {
        uminePlaceCheck.setModifyDate(new Date());
        return uminePlaceCheckMapper.updateByPrimaryKey(uminePlaceCheck);
    }

    @Override
    public int deleteUminePlaceCheckById(String id) {
        Object result = uminePlaceCheckMapper.getUminePlaceCheckRelationCount(id);
        if (result != null) {
            return uminePlaceCheckMapper.deleteUminePlaceCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getUminePlaceCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceCheckExtend> list = uminePlaceCheckMapper.getUminePlaceCheckList(map);

        PageInfo<UminePlaceCheckExtend> pageInfo = new PageInfo<UminePlaceCheckExtend>(list);

        PagingEntity<UminePlaceCheckExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUminePlaceCheckById(String id) {
        UminePlaceCheck result = uminePlaceCheckMapper.getUminePlaceCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportUmineplaceCheck(Page page, HttpServletResponse response) {

        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceCheckExtend> list = uminePlaceCheckMapper.getUminePlaceCheckList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<UminePlaceFileCheckExtend> uminePlaceFileCheckExtendList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (UminePlaceCheckExtend uminePlaceCheckExtend : list) {
                String[] strs = new String[]{
                        uminePlaceCheckExtend.getId(),
                        uminePlaceCheckExtend.getUmineName(),
                        uminePlaceCheckExtend.getUminePlaceName(),
                        uminePlaceCheckExtend.getTypeValue(),
                        uminePlaceCheckExtend.getStageValue(),
                        DateUtils.DateToString(uminePlaceCheckExtend.getCheckDate())
                };
                cloumnValues.add(strs);

                //审评信息
                Map<String,Object> map1  = new HashMap<>();
                map1.put("umineplaceId",uminePlaceCheckExtend.getId());
                List<UminePlaceFileCheckExtend> list1 = uminePlaceFileCheckMapper.getUminePlaceFileCheckList(map1);
                if(list1!=null&&list1.size()>0){
                    uminePlaceFileCheckExtendList.addAll(list1);
                }
            }
        }

        String[] cloumnNames = new String[]{
                "主编号",
                "营运单位",
                "铀尾矿(渣)库名称",
                "审评类型",
                "审评阶段",
                "审评时间"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "铀尾矿(渣)库审评信息列表", cloumnNames, cloumnValues);


        //审评评文件信息
        String[] cloumnNames1 = new String[]{
                "主编号",
                "文件类型",
                "文件文号",
                "文件时间"
        };

        cloumnValues = new ArrayList<>();
        if(uminePlaceFileCheckExtendList.size()>0){
            for (UminePlaceFileCheckExtend uminePlaceFileCheckExtend : uminePlaceFileCheckExtendList) {
                String[] strs = new String[]{
                        uminePlaceFileCheckExtend.getCheckUminePlaceId(),
                        uminePlaceFileCheckExtend.getUminePlaceCheckFileTypeValue(),
                        uminePlaceFileCheckExtend.getFileNo(),
                        DateUtils.DateToString(uminePlaceFileCheckExtend.getFileDate())
                };
                cloumnValues.add(strs);
            }
        }

        wb = ExportExcel.getHssfWorkBook(wb, "审评文件列表", cloumnNames1, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("铀尾矿(渣)库审评信息列表", "utf-8") + ".xls");
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