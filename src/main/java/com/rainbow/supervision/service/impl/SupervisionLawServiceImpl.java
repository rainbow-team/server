package com.rainbow.supervision.service.impl;

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
import com.rainbow.supervision.dao.SupervisionLawMapper;
import com.rainbow.supervision.domain.SupervisionLaw;
import com.rainbow.supervision.domain.SupervisorTrain;
import com.rainbow.supervision.service.LawSupervisionService;
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
@Service("SupervisionLawService")
public class SupervisionLawServiceImpl extends BaseService<SupervisionLaw> implements LawSupervisionService {

    @Autowired
    SupervisionLawMapper supervisionLawMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addLaw(SupervisionLaw law) {
        law.setId(GuidHelper.getGuid());
        law.setCreateDate(new Date());
        law.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(law.getAttachmentList(),law.getId());
        return supervisionLawMapper.insert(law);
    }

    @Override
    public int modifyLaw(SupervisionLaw law) {
        law.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(law.getAttachmentList(),law.getId());
        return supervisionLawMapper.updateByPrimaryKey(law);
    }

    @Override
    public ResponseBo getLawList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionLaw> list = supervisionLawMapper.getLawList(map);

        PageInfo<SupervisionLaw> pageInfo = new PageInfo<SupervisionLaw>(list);

        PagingEntity<SupervisionLaw> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public void exportLaw(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionLaw> list = supervisionLawMapper.getLawList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<SupervisionLaw> laws = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (SupervisionLaw law : list) {
                String[] strs = new String[]{
                        law.getCode(),
                        law.getName(),
                        DateUtils.DateToString(law.getReleaseDate())
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "法规文号",
                "法规名称",
                "发布时间"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "监管法规信息", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("监管法规信息", "utf-8") + ".xls");
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
