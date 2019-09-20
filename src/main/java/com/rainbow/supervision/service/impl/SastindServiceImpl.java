package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionSastindMapper;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.domain.SupervisionTrainRecordExtend;
import com.rainbow.supervision.domain.Welder;
import com.rainbow.supervision.domain.extend.SupervisorExtend;
import com.rainbow.supervision.service.SastindService;
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
@Service("SupervisionSastindService")
public class SastindServiceImpl extends BaseService<SupervisionSastind> implements SastindService {

    @Autowired
    SupervisionSastindMapper sastindMapper;
    /**
     * 保存国防科工局信息
     * @param
     * @return
     */
    @Override
    @SystemLog(description="添加国防科工局信息")
    public int addSastind(SupervisionSastind sastind) {
      sastind.setId(GuidHelper.getGuid());
      sastind.setCreateDate(new Date());
      sastind.setModifyDate(new Date());
      return sastindMapper.insert(sastind);
    }

    /**
     * 修改国防科工局信息
     * @param sastind
     * @return
     */
    @Override
    @SystemLog(description="修改国防科工局信息")
    public int modifySastind(SupervisionSastind sastind) {
        sastind.setModifyDate(new Date());
        return sastindMapper.updateByPrimaryKey(sastind);
    }

    @Override
    public ResponseBo getSastindList(Page page){
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionSastind> list = sastindMapper.getSastindList(map);

        PageInfo<SupervisionSastind> pageInfo = new PageInfo<SupervisionSastind>(list);

        PagingEntity<SupervisionSastind> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo importSastind(List<SupervisionSastind> list){

        for (SupervisionSastind supervisionSastind:list
             ) {
            supervisionSastind.setId(GuidHelper.getGuid());
            supervisionSastind.setCreateDate(new Date());
            supervisionSastind.setModifyDate(new Date());

            sastindMapper.insert(supervisionSastind);
        }

        return ResponseBo.ok();
    }

    @Override
    public void exportSastind(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionSastind> list = sastindMapper.getSastindList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (SupervisionSastind sastind : list) {
                String[] strs = new String[]{
                        sastind.getName(),
                        sastind.getOrgSet(),
                        sastind.getDuty(),
                        sastind.getLeader(),
                        sastind.getLeaderTel(),
                        sastind.getSecurityLeader(),
                        sastind.getSecurityLeaderTel(),
                        sastind.getPermitLeader(),
                        sastind.getPermitLeaderTel(),
                        sastind.getSuperviseLeader(),
                        sastind.getSecurityLeaderTel(),
                        sastind.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "司局名称",
                "处室设置",
                "工作职责",
                "司领导",
                "司领导电话",
                "分管核安全司领导",
                "分管核安全司领导电话",
                "核安全许可处室领导",
                "核安全许可处室领导电话",
                "核安全监督处室领导",
                "核安全监督处室领导电话",
                "备注"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "国防科工局基本信息", cloumnNames, cloumnValues);




        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("国防科工局基本信息", "utf-8") + ".xls");
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
