package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.GroupExtend;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.FacService;
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
@Service("equipdepartservice")
public class EquipDepartServiceImpl extends BaseService<EquipDepart> implements EquipDepartService {


    @Autowired
    EquipDepartMapper equipDepartMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addEquipDepart(EquipDepart equipDepart) {
        equipDepart.setId(GuidHelper.getGuid());
        equipDepart.setCreateDate(new Date());
        equipDepart.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(equipDepart.getAttachmentList(),equipDepart.getId());
        return equipDepartMapper.insert(equipDepart);
    }

    @Override
    public int modifyEquipDepart(EquipDepart equipDepart) {
        equipDepart.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(equipDepart.getAttachmentList(),equipDepart.getId());
        return equipDepartMapper.updateByPrimaryKey(equipDepart);
    }

    @Override
    public ResponseBo getEquipDepartList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<EquipDepart> list = equipDepartMapper.getEquipDepartList(map);

        PageInfo<EquipDepart> pageInfo = new PageInfo<EquipDepart>(list);

        PagingEntity<EquipDepart> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public int deleteEquipDepartById(String id) {
        Object result = equipDepartMapper.getEquipDepartRelationCount(id);
        if (result != null) {
            return equipDepartMapper.deleteEquipDepartById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getEquipDepartById(String id) {
        EquipDepart result = equipDepartMapper.getEquipDepartById(id);

        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public void exportEquipDepart(Page page,HttpServletResponse response){
        Map<String, Object> map = page.getQueryParameter();
        List<EquipDepart> list = equipDepartMapper.getEquipDepartList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (EquipDepart equipDepart:list) {
                String[] strs = new String[] {
                        equipDepart.getName(),
                        equipDepart.getSurvey(),
                        equipDepart.getProduct(),
                        equipDepart.getAddress(),
                        equipDepart.getEmergencyTel(),
                        equipDepart.getFax(),
                        equipDepart.getOwner(),
                        equipDepart.getLeader(),
                        equipDepart.getLeaderTel(),
                        equipDepart.getDepartQualityLeader(),
                        equipDepart.getDepartQualityLeaderTel(),
                        equipDepart.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "单位名称","基本概况","主要产品","地址","应急电话","传真","法人代表",
                "主管质量领导","主管质量领导电话","质量部门领导","质量部门领导电话","备注"
        };
        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核设备单位信息", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("核设备单位信息", "utf-8") + ".xls");
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