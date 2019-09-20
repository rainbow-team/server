package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.supervision.dao.OrgMapper;
import com.rainbow.supervision.dao.RelationOrgNatureMapper;
import com.rainbow.supervision.domain.Org;
import com.rainbow.supervision.domain.RelationOrgNature;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.domain.extend.OrgExtend;
import com.rainbow.supervision.service.OrgService;
import com.rainbow.system.dao.UserMapper;
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
@Service("orgservice")
public class OrgServiceImpl extends BaseService<Org> implements OrgService {

    @Autowired
    OrgMapper orgMapper;

    @Autowired
    RelationOrgNatureMapper relationOrgNatureMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public int addOrg(Org org) {

        org.setId(GuidHelper.getGuid());
        org.setCreateDate(new Date());
        org.setModifyDate(new Date());
        if (org.getNature() != null) {
            insertRelation(org);
        }
        return orgMapper.insert(org);
    }

    @Override
    public int modifyOrg(Org org) {
        org.setModifyDate(new Date());
        //根据监督机构的ID，删除关联表中的关系
        relationOrgNatureMapper.deleteRelationByOrgId(org.getId());

        if (org.getNature() != null) {
            insertRelation(org);
        }
        return orgMapper.updateByPrimaryKey(org);
    }

    @Override
    public void deleteOrgByIds(List<String> ids) {
        for(String id:ids){
            //删除对应关系，然后再删除监管机构信息
            relationOrgNatureMapper.deleteRelationByOrgId(id);
            orgMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public ResponseBo getOrgList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<OrgExtend> list = orgMapper.getOrgList(map);

        PageInfo<OrgExtend> pageInfo = new PageInfo<OrgExtend>(list);

        PagingEntity<OrgExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }


    @Override
    public ResponseBo getOrgById(String id) {
        OrgExtend result = orgMapper.getOrgById(id);
        //SystemUser systemUser = userMapper.selectByPrimaryKey(result.getCreaterId());
        //result.setCreatorName(systemUser.getUsername());

        return ResponseBo.ok(result);
        //return null;
    }

    /**
     * 建立授权监管机构单位信息关系
     *
     * @param org
     */
    private void insertRelation(Org org) {
        List<SystemConfig> natureList = org.getNature();
        for (SystemConfig systemConfig : natureList) {
            RelationOrgNature relationOrgNature = new RelationOrgNature();
            relationOrgNature.setId(GuidHelper.getGuid());
            relationOrgNature.setOrgId(org.getId());
            relationOrgNature.setNatureId(systemConfig.getId());
            relationOrgNatureMapper.insert(relationOrgNature);
        }
    }

    @Override
    public void exportOrg(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<OrgExtend> list = orgMapper.getOrgList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<OrgExtend> orgExtendList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (OrgExtend orgExtend : list) {
                String[] strs = new String[]{
                        orgExtend.getName(),
                        orgExtend.getNatureValue(),
                        orgExtend.getDuty(),
                        orgExtend.getLeader(),
                        orgExtend.getLeaderTel(),
                        orgExtend.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "监管机构",
                "机构性质",
                "工作职责",
                "机构领导",
                "机构领导电话",
                "备注"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "授权监管机构基本信息", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("授权监管机构基本信息", "utf-8") + ".xls");
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
