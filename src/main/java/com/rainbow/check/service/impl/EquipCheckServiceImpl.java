package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.EquipCheckMapper;
import com.rainbow.check.dao.EquipFileCheckMapper;
import com.rainbow.check.dao.FacCheckMapper;
import com.rainbow.check.domain.*;
import com.rainbow.check.service.EquipCheckService;
import com.rainbow.check.service.FacCheckService;
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
@Service("equipcheckservice")
public class EquipCheckServiceImpl extends BaseService<EquipCheck> implements EquipCheckService {

    @Autowired
    EquipCheckMapper equipCheckMapper;

    @Autowired
    EquipFileCheckMapper equipFileCheckMapper;

    @Override
    public int addEquipCheck(EquipCheck equipCheck) {
        equipCheck.setId(GuidHelper.getGuid());
        equipCheck.setCreateDate(new Date());
        equipCheck.setModifyDate(new Date());
        return equipCheckMapper.insert(equipCheck);
    }

    @Override
    public int modifyEquipCheck(EquipCheck equipCheck) {
        equipCheck.setModifyDate(new Date());
        return equipCheckMapper.updateByPrimaryKey(equipCheck);
    }

    @Override
    public int deleteEquipCheckById(String id) {
        Object result = equipCheckMapper.getEquipCheckRelationCount(id);
        if (result != null) {
            return equipCheckMapper.deleteEquipCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getEquipCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<EquipCheckExtend> list = equipCheckMapper.getEquipCheckList(map);

        PageInfo<EquipCheckExtend> pageInfo = new PageInfo<EquipCheckExtend>(list);

        PagingEntity<EquipCheckExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getEquipCheckById(String id) {
        EquipCheck result = equipCheckMapper.getEquipCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportEquipCheck(Page page, HttpServletResponse response) {

        Map<String, Object> map = page.getQueryParameter();
        List<EquipCheckExtend> list = equipCheckMapper.getEquipCheckList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<EquipFileCheckExtend> equipFileCheckExtendList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (EquipCheckExtend equipCheckExtend : list) {
                String[] strs = new String[]{
                        equipCheckExtend.getId(),
                        equipCheckExtend.getName(),
                        equipCheckExtend.getEquipDepartName(),
                        equipCheckExtend.getServiceDepartName(),
                        equipCheckExtend.getFacName(),
                        equipCheckExtend.getTypeValue(),
                        equipCheckExtend.getLevelValue(),
                        equipCheckExtend.getStageValue(),
                        DateUtils.DateToString(equipCheckExtend.getCheckDate())
                };
                cloumnValues.add(strs);

                //审评文件信息
                Map<String,Object> map1  = new HashMap<>();
                map1.put("equipId",equipCheckExtend.getId());
                List<EquipFileCheckExtend> list1 = equipFileCheckMapper.getEquipFileCheckList(map1);
                if(list1!=null&&list1.size()>0){
                    equipFileCheckExtendList.addAll(list1);
                }
            }
        }

        String[] cloumnNames = new String[]{
                "主编号",
                "设备名称",
                "核设备单位",
                "核设施营运单位",
                "核设施名称",
                "设备类别",
                "核安全级别",
                "审评类型",
                "审评时间"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核安全设备审评信息列表", cloumnNames, cloumnValues);


        //培审评文件信息
        String[] cloumnNames1 = new String[]{
                "主编号",
                "文件类型",
                "文件文号",
                "文件时间"
        };

        cloumnValues = new ArrayList<>();
        if(equipFileCheckExtendList.size()>0){
            for (EquipFileCheckExtend equipFileCheckExtend : equipFileCheckExtendList) {
                String[] strs = new String[]{
                        equipFileCheckExtend.getCheckEquipId(),
                        equipFileCheckExtend.getEquipCheckFileTypeValue(),
                        equipFileCheckExtend.getFileNo(),
                        DateUtils.DateToString(equipFileCheckExtend.getFileDate())
                };
                cloumnValues.add(strs);
            }
        }

        wb = ExportExcel.getHssfWorkBook(wb, "审评文件列表", cloumnNames1, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("核安全设备审评信息列表", "utf-8") + ".xls");
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