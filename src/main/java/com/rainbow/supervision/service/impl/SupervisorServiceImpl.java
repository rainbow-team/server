package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.dao.FileInfoMapper;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.*;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.supervision.controller.domain.SupervisionSupervisorResponse;
import com.rainbow.supervision.dao.SupervisionTrainRecordMapper;
import com.rainbow.supervision.dao.SupervisorMapper;
import com.rainbow.supervision.domain.SupervisionTrainRecordExtend;
import com.rainbow.supervision.domain.Supervisor;
import com.rainbow.supervision.domain.extend.SupervisorExtend;
import com.rainbow.supervision.service.SupervisorService;
import com.rainbow.system.domain.SystemUser;
import net.sf.ehcache.CacheManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 13260 on 2019/5/11.
 */
@Service("supervisionSupervisorService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SupervisorServiceImpl extends BaseService<Supervisor> implements SupervisorService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SupervisorMapper supervisorMapper;

    @Autowired
    private FileInfoMapper FileInfoMapper;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SupervisionTrainRecordMapper supervisionTrainRecordMapper;

    @Override
    public int addSupervisor(Supervisor supervisor) {
        supervisor.setId(GuidHelper.getGuid());
        supervisor.setCreateDate(new Date());
        supervisor.setModifyDate(new Date());
        return supervisorMapper.insert(supervisor);
    }

    @Override
    public int modifySupervisor(Supervisor supervisor) {
        supervisor.setModifyDate(new Date());
       return updateNotNull(supervisor);
        //return supervisorMapper.updateByPrimaryKey(supervisor);
    }

    @Override
    public ResponseBo deleteSupervisorById(String id) {
        int result = 0;
        int count = supervisionTrainRecordMapper.getRecordCountBySupervisorId(id);
        if (count == 0) {
            result = super.deleteByKey(id);
        } else {
            return ResponseBo.error("已经被关联，删除失败!");
        }
        return result == 0 ? ResponseBo.error("删除失败") : ResponseBo.ok("删除成功");
    }

    @Override
    public ResponseBo getSupervisorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisorExtend> list = supervisorMapper.getSupervisorList(map);

        PageInfo<SupervisorExtend> pageInfo = new PageInfo<SupervisorExtend>(list);

        PagingEntity<SupervisorExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getSupervisorById(String id) {
        SupervisorExtend result = supervisorMapper.getSupervisorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportSupervisor(Page page,HttpServletResponse response){

        Map<String, Object> map = page.getQueryParameter();
        List<SupervisorExtend> list = supervisorMapper.getSupervisorList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<SupervisionTrainRecordExtend> supervisionTrainRecordExtendList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (SupervisorExtend supervisorExtend : list) {
                String[] strs = new String[]{
                        supervisorExtend.getName(),
                        supervisorExtend.getIdentity(),
                        DateUtils.DateToString(supervisorExtend.getBirthday()),
                        supervisorExtend.getOrgName(),
                        supervisorExtend.getTypeValue(),
                        DateUtils.DateToString(supervisorExtend.getEntryDate()),
                        supervisorExtend.getTitleName(),
                        supervisorExtend.getPost(),
                        supervisorExtend.getPoliticalValue(),
                        supervisorExtend.getSex()==0?"男":"女",
                        supervisorExtend.getContact(),
                        supervisorExtend.getContinueTime(),
                        supervisorExtend.getEducationValue(),
                        supervisorExtend.getDegreeValue(),
                        supervisorExtend.getEducationValue(),
                        DateUtils.DateToString(supervisorExtend.getEducateDate()),
                        supervisorExtend.getMajor(),
                        supervisorExtend.getNote(),
                        DateUtils.DateToString(supervisorExtend.getExpireDate())
                };
                cloumnValues.add(strs);

                //培训信息
                Map<String,Object> map1  = new HashMap<>();
                map1.put("supervisorId",supervisorExtend.getId());
                List<SupervisionTrainRecordExtend> list1 = supervisionTrainRecordMapper.getTrainRecordList(map1);
                if(list1!=null&&list1.size()>0){
                    supervisionTrainRecordExtendList.addAll(list1);
                }
            }
        }

        String[] cloumnNames = new String[]{
                "姓名",
                "身份证号",
                "出生年月",
                "核安全授权监管机构名称",
                "监督员类别名称",
                "入职时间",
                "职称名称",
                "职务",
                "政治面貌名称",
                "性别",
                "联系方式",
                "从事核安全工作时间",
                "学历名称",
                "学位名称",
                "毕业院校",
                "毕业时间",
                "专业",
                "备注",
                "过期时间"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核安全监督员信息", cloumnNames, cloumnValues);


        //培训信息
        String[] cloumnNames1 = new String[]{
                "身份证号",
                "培训班次",
                "培训成绩",
                "监督员证号",
                "发证日期",
                "到期时间"
        };

        cloumnValues = new ArrayList<>();
        if(supervisionTrainRecordExtendList.size()>0){
            for (SupervisionTrainRecordExtend supervisionTrainRecordExtend : supervisionTrainRecordExtendList) {
                String[] strs = new String[]{
                        supervisionTrainRecordExtend.getIdentity(),
                        supervisionTrainRecordExtend.getTrainClass(),
                        supervisionTrainRecordExtend.getScore(),
                        supervisionTrainRecordExtend.getNumber(),
                        DateUtils.DateToString(supervisionTrainRecordExtend.getIssueDate()),
                        DateUtils.DateToString(supervisionTrainRecordExtend.getExpireDate())
                };
                cloumnValues.add(strs);
            }
        }

        wb = ExportExcel.getHssfWorkBook(wb, "培训信息", cloumnNames1, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("核安全监督员信息", "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            out.write(xlsBytes);
            out.close();
        }catch (Exception e){

        }

    }

    @Override
    public ResponseBo importSupervisor(HttpServletRequest request){
        Multipart part = new Multipart();
        //获取前端传过来的file
        MultipartFile file = part.getUploadFile(request);
        FileInputStream inputStream = null;

        ResponseBo result = new ResponseBo();

        String msg = "";
        try {
            if (file != null) {
                //转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                //将导入的excel转化为实体
                List<SupervisorExtend> list = ExcelHelper.convertToList(SupervisorExtend.class, fileName, inputStream, 2, 20,0);

                inputStream.close();

                if(list.size()==0){
                    return ResponseBo.error("文件内容为空");
                }


                //校验
                for (int i=0;i<list.size();i++){

                }

                if(!msg.isEmpty()){
                    return ResponseBo.error(msg);
                }else{
                    //插入数据库
                    SystemUser user = UserUtils.getCurrentUser(cacheManager);
                    if(user==null){
                        user = new SystemUser();
                    }

                    for (SupervisorExtend supervisorExtend:list) {

                        supervisorExtend.setIsImport(1);
                        supervisorExtend.setId(GuidHelper.getGuid());
                        supervisorExtend.setCreateDate(new Date());
                        supervisorExtend.setModifyDate(new Date());
                        supervisorExtend.setCreatorId(user.getId());
                        supervisorExtend.setModifyId(user.getId());

                        supervisorMapper.insert(supervisorExtend);

                    }
                }


            }
        } catch (Exception e) {

        }

        return ResponseBo.ok();
    }
}
