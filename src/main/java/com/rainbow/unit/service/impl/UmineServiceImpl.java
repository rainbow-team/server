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
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.UmineMapper;
import com.rainbow.unit.dao.UmineMountainMapper;
import com.rainbow.unit.dao.UmineplaceMapper;
import com.rainbow.unit.domain.*;
import com.rainbow.unit.service.FacService;
import com.rainbow.unit.service.UmineService;
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
@Service("umineservice")
public class UmineServiceImpl extends BaseService<Umine> implements UmineService {

    @Autowired
    UmineMapper umineMapper;

    @Autowired
    UmineMountainMapper umineMountainMapper;

    @Autowired
    UmineplaceMapper umineplaceMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUmine(Umine umine) {
        umine.setId(GuidHelper.getGuid());
        umine.setCreateDate(new Date());
        umine.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(umine.getAttachmentList(),umine.getId());
        return umineMapper.insert(umine);
    }

    @Override
    public int modifyUmine(Umine umine) {
        umine.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(umine.getAttachmentList(),umine.getId());
        return umineMapper.updateByPrimaryKey(umine);
    }

    @Override
    public ResponseBo getUmineList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineExtend> list = umineMapper.getUmineList(map);

        PageInfo<UmineExtend> pageInfo = new PageInfo<UmineExtend>(list);

        PagingEntity<UmineExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineById(String id) {

        UmineExtend result = umineMapper.getUmineById(id);

        if (result != null) {
            int mountainNum = umineMountainMapper.getMountainSumByUmineId(result.getId());

            int placeNum = umineplaceMapper.getUminePlaceSumByUmineId(result.getId());

            result.setUmineMountainNum(mountainNum);
            result.setUmineplaceNum(placeNum);

            return ResponseBo.ok(result);
        }


        return ResponseBo.error("获取失败");
    }

    @Override
    public int deleteUmineById(String id) {
        Object result = umineMapper.getUmineRelationCount(id);
        if (result != null) {
            return umineMapper.deleteUmineById(id);
        }
        return 0;
    }

    @Override
    public void exportUmine(Page page,HttpServletResponse response){

        Map<String, Object> map = page.getQueryParameter();
        List<UmineExtend> list = umineMapper.getUmineList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (UmineExtend umine:list) {
                String[] strs = new String[] {
                        umine.getName(),
                        umine.getGroupName(),
                        umine.getSurvey(),
                        umine.getFeature(),
                        umine.getCode(),
                        umine.getAddress(),
                        umine.getEmergencyTel(),
                        umine.getFax(),
                        umine.getOwner(),
                        umine.getLeader(),
                        umine.getLeaderTel(),
                        umine.getDepartLeader(),
                        umine.getDepartLeaderTel(),
                        umine.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "单位名称","集团名称","基本概况","厂址特征","代号","地址","应急电话","传真","法人代表",
                "主管安全领导","主管安全领导电话","安全部门领导","安全部门领导电话","备注"
        };
        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "铀矿冶单位信息", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("铀矿冶单位信息", "utf-8") + ".xls");
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
