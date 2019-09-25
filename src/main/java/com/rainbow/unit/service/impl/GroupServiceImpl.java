package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.BreakCheckerMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.service.BreakCheckerService;
import com.rainbow.unit.dao.GroupMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.dao.UmineMapper;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.domain.GroupExtend;
import com.rainbow.unit.domain.UmineMountain;
import com.rainbow.unit.service.GroupService;
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
@Service("groupservice")
public class GroupServiceImpl extends BaseService<Group> implements GroupService {


    @Autowired
    GroupMapper groupMapper;

    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    @Autowired
    UmineMapper umineMapper;


    @Override
    public int addGroup(Group group) {
        group.setId(GuidHelper.getGuid());
        group.setCreateDate(new Date());
        group.setModifyDate(new Date());
        return groupMapper.insert(group);
    }

    @Override
    public int modifyGroup(Group group) {
        group.setModifyDate(new Date());
        return groupMapper.updateByPrimaryKey(group);
    }

    @Override
    public ResponseBo getGroupList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Group> list = groupMapper.getGroupList(map);

        PageInfo<Group> pageInfo = new PageInfo<Group>(list);

        PagingEntity<Group> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getGroupById(String id) {

        GroupExtend result = groupMapper.getGroupById(id);


        if (result != null) {
            int serviceDepartNum = serviceDepartMapper.getSumByGroupId(result.getId());

            int umineNum = umineMapper.getSumByGroupId(result.getId());

            result.setServiceDepartNum(serviceDepartNum);
            result.setUmineNum(umineNum);
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public int deleteGroupById(String id) {
        Object result = groupMapper.getGroupRelationCount(id);
        if (result != null) {
            return groupMapper.deleteGroupById(id);
        }
        return 0;
    }

    @Override
    public void exportGroup(Page page,HttpServletResponse response){

        Map<String, Object> map = page.getQueryParameter();
        List<Group> list = groupMapper.getGroupList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (Group group:list) {
                String[] strs = new String[] {
                        group.getName(),
                        group.getSurvey(),
                        group.getAddress(),
                        group.getEmergencyTel(),
                        group.getFax(),
                        group.getOwner(),
                        group.getLeader(),
                        group.getLeaderTel(),
                        group.getDepartLeader(),
                        group.getDepartLeaderTel(),
                        group.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "集团名称","基本概况","地址","应急电话","传真","法人代表",
                "主管安全领导","主管安全领导电话","安全部门领导","安全部门领导电话","备注"
        };
        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "集团信息", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("集团信息", "utf-8") + ".xls");
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
