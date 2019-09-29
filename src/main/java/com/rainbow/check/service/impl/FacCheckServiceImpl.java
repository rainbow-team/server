package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.FacCheckMapper;
import com.rainbow.check.dao.FacFileCheckMapper;
import com.rainbow.check.domain.*;
import com.rainbow.check.service.FacCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.permit.dao.ActivityPermitMapper;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.service.ActivityPermitService;
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
@Service("faccheckservice")
public class FacCheckServiceImpl extends BaseService<FacCheck> implements FacCheckService {

    @Autowired
    FacCheckMapper facCheckMapper;

    @Autowired
    FacFileCheckMapper facFileCheckMapper;

    @Override
    public int addFacCheck(FacCheck facCheck) {
        facCheck.setId(GuidHelper.getGuid());
        facCheck.setCreateDate(new Date());
        facCheck.setModifyDate(new Date());
        return facCheckMapper.insert(facCheck);
    }

    @Override
    public int modifyFacCheck(FacCheck facCheck) {
        facCheck.setModifyDate(new Date());
        return facCheckMapper.updateByPrimaryKey(facCheck);
    }

    @Override
    public int deleteFacCheckById(String id) {
        Object result = facCheckMapper.getFacCheckRelationCount(id);
        if (result != null) {
            return facCheckMapper.deleteFacCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getFacCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacCheckExtend> list = facCheckMapper.getFacCheckList(map);

        PageInfo<FacCheckExtend> pageInfo = new PageInfo<FacCheckExtend>(list);

        PagingEntity<FacCheckExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacCheckById(String id) {
        FacCheck result = facCheckMapper.getFacCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportFacCheck(Page page, HttpServletResponse response) {

        Map<String, Object> map = page.getQueryParameter();
        List<FacCheckExtend> list = facCheckMapper.getFacCheckList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<FacFileCheckExtend> facFileCheckExtendList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (FacCheckExtend facCheckExtend : list) {

                String[] strs = new String[]{
                        facCheckExtend.getId(),
                        facCheckExtend.getServiceDepartName(),
                        facCheckExtend.getFacName(),
                        facCheckExtend.getTypeValue(),
                        facCheckExtend.getStageValue(),
                        DateUtils.DateToString(facCheckExtend.getCheckDate())
                };
                cloumnValues.add(strs);

                //审评文件
                Map<String,Object> map1  = new HashMap<>();
                map1.put("facId",facCheckExtend.getId());
                List<FacFileCheckExtend> list1 = facFileCheckMapper.getFacFileCheckList(map1);
                if(list1!=null&&list1.size()>0){
                    facFileCheckExtendList.addAll(list1);
                }
            }
        }

        String[] cloumnNames = new String[]{
                "主编号",
                "营运单位",
                "设施名称",
                "审评类型",
                "审评阶段",
                "审评时间"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核设施审评信息列表", cloumnNames, cloumnValues);


        //培审评文件信息
        String[] cloumnNames1 = new String[]{
                "主编号",
                "文件名称",
                "文件类型",
                "文件文号",
                "文件时间"
        };

        cloumnValues = new ArrayList<>();
        if(facFileCheckExtendList.size()>0){
            for (FacFileCheckExtend facFileCheckExtend : facFileCheckExtendList) {
                String[] strs = new String[]{
                        facFileCheckExtend.getId(),
                        facFileCheckExtend.getFileName(),
                        facFileCheckExtend.getFacCheckFileTypeValue(),
                        facFileCheckExtend.getFileNo(),
                        DateUtils.DateToString(facFileCheckExtend.getFileDate())
                };
                cloumnValues.add(strs);
            }
        }

        wb = ExportExcel.getHssfWorkBook(wb, "审评文件列表", cloumnNames1, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("核设施审评信息列表", "utf-8") + ".xls");
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