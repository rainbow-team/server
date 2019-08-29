package com.rainbow.dataMigration.service.impl;

import com.rainbow.attachment.dao.FileInfoMapper;
import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.check.dao.FacCheckMapper;
import com.rainbow.check.dao.FacFileCheckMapper;
import com.rainbow.check.domain.FacCheck;
import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.ZipUtils;
import com.rainbow.dataMigration.service.DataMigrationService;
import com.rainbow.monitor.dao.CheckFileMonitorMapper;
import com.rainbow.monitor.dao.CheckMonitorMapper;
import com.rainbow.monitor.dao.DailyMonitorMapper;
import com.rainbow.monitor.dao.WitnessMonitorMapper;
import com.rainbow.monitor.domain.CheckFileMonitor;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.domain.DailyMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.security.dao.FacSecurityMapper;
import com.rainbow.security.domain.FacSecurity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipOutputStream;

/**
 * Created by 13260 on 2019/8/27.
 */
@Service("DataMigrationService")
public class DataMigrationServiceImpl implements DataMigrationService {

    @Autowired
    FacCheckMapper facCheckMapper;

    @Autowired
    FacFileCheckMapper facFileCheckMapper;

    @Autowired
    DailyMonitorMapper dailyMonitorMapper;

    @Autowired
    CheckMonitorMapper checkMonitorMapper;

    @Autowired
    CheckFileMonitorMapper checkFileMonitorMapper;

    @Autowired
    WitnessMonitorMapper witnessMonitorMapper;

    @Autowired
    FacSecurityMapper facSecurityMapper;

    @Autowired
    FileInfoMapper fileInfoMapper;

    @Override
    public  void exportData(String type,HttpServletResponse response) {

        String[] tables = type.split(",");

        List<String[]> cloumnValues = new ArrayList<>();
        List<FileInfo> fileInfoList = new ArrayList<>();
        List<ByteArrayOutputStream> listBaos = new ArrayList<>();
        List<String> listName = new ArrayList<>();

        Map<String,String> map = new HashMap<>();

        try  {

            ZipOutputStream outputStream = new ZipOutputStream(response.getOutputStream());
            if (tables != null && tables.length > 0) {

                for (String str : tables) {
                    switch (str) {
                        //核设施审评
                        case "1":

                            cloumnValues = new ArrayList<>();

                            List<FacCheck> facCheckList = facCheckMapper.selectAll();
                            if (facCheckList != null && facCheckList.size() > 0) {

                                for (FacCheck facCheck : facCheckList) {
                                    String[] strs = new String[]{
                                            facCheck.getId(),
                                            facCheck.getServiceId(),
                                            facCheck.getFacId(),
                                            facCheck.getTypeId(),
                                            facCheck.getStageId(),
                                            DateToString(facCheck.getCheckDate()),
                                            facCheck.getNote(),
                                            IntegerToString(facCheck.getIsImport()),
                                            facCheck.getCreatorId(),
                                            DateToString(facCheck.getCreateDate()),
                                            facCheck.getModifyId(),
                                            DateToString(facCheck.getModifyDate())
                                    };
                                    cloumnValues.add(strs);
                                }
                            }
                            String[] cloumnNames = new String[]{"id",
                                    "service_id",
                                    "fac_id",
                                    "type_id",
                                    "stage_id",
                                    "check_date",
                                    "note",
                                    "is_import",
                                    "creator_id",
                                    "create_date",
                                    "modify_id",
                                    "modify_date"
                            };
                            HSSFWorkbook wb = new HSSFWorkbook();
                            wb = ExportExcel.getHssfWorkBook(wb, "核设施审评", cloumnNames, cloumnValues);

                            List<FacFileCheck> facFileCheckList = facFileCheckMapper.selectAll();

                            cloumnValues = new ArrayList<>();

                            if (facFileCheckList != null && facFileCheckList.size() > 0) {

                                for (FacFileCheck facFileCheck : facFileCheckList) {
                                    String[] strs = new String[]{
                                            facFileCheck.getId(),
                                            facFileCheck.getCheckFacId(),
                                            facFileCheck.getFileName(),
                                            facFileCheck.getFacCheckFileTypeId(),
                                            DateToString(facFileCheck.getFileDate()),
                                            facFileCheck.getFileNo(),
                                            IntegerToString(facFileCheck.getIsImport())
                                    };
                                    cloumnValues.add(strs);
                                }
                            }

                            String[] cloumnNamesFacFile = new String[]{"id",
                                    "check_fac_id",
                                    "file_name",
                                    "fac_check_file_type_id",
                                    "file_date",
                                    "file_no",
                                    "is_import"
                            };

                            wb = ExportExcel.getHssfWorkBook(wb, "核设施审评阶段文件表", cloumnNamesFacFile, cloumnValues);

                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            wb.write(baos);
                            listBaos.add(baos);

                            listName.add("核设施审评阶段文件表.xls");
                            //附件
                            map.put("tablename","check_fac_file");
                            fileInfoList = fileInfoMapper.getFileInfoByTableName(map);


                            break;
                        //日常监督信息
                        case "2":

                            cloumnValues = new ArrayList<>();

                            List<DailyMonitor> dailyMonitorList = dailyMonitorMapper.selectAll();

                            if (dailyMonitorList != null && dailyMonitorList.size() > 0) {

                                for (DailyMonitor dilyMonitor : dailyMonitorList) {
                                    String[] strs = new String[]{
                                            dilyMonitor.getId(),
                                            dilyMonitor.getServiceId(),
                                            dilyMonitor.getFacId(),
                                            dilyMonitor.getStatusId(),
                                            dilyMonitor.getOrgId(),
                                            dilyMonitor.getFileTypeId(),
                                            dilyMonitor.getFileName(),
                                            DateToString(dilyMonitor.getFileDate()),
                                            dilyMonitor.getNote(),
                                            IntegerToString(dilyMonitor.getIsImport()),
                                            dilyMonitor.getCreatorId(),
                                            DateToString(dilyMonitor.getCreateDate()),
                                            dilyMonitor.getModifyId(),
                                            DateToString(dilyMonitor.getModifyDate())
                                    };
                                    cloumnValues.add(strs);
                                }
                            }
                            String[] cloumnNamesDaily = new String[]{"id",
                                    "service_id",
                                    "fac_id",
                                    "status_id",
                                    "org_id",
                                    "file_type_id",
                                    "file_name",
                                    "file_date",
                                    "note",
                                    "is_import",
                                    "creator_id",
                                    "create_date",
                                    "modify_id",
                                    "modify_date"
                            };

                            HSSFWorkbook wbDaily = new HSSFWorkbook();
                            wbDaily = ExportExcel.getHssfWorkBook(wbDaily, "日常监督信息", cloumnNamesDaily, cloumnValues);

                            ByteArrayOutputStream baosDaily = new ByteArrayOutputStream();
                            wbDaily.write(baosDaily);
                            listBaos.add(baosDaily);

                            listName.add("日常监督信息.xls");

                            break;
                        //监督检查信息
                        case "3":

                            cloumnValues = new ArrayList<>();

                            List<CheckMonitor>  checkMonitorList =  checkMonitorMapper.selectAll();

                            if (checkMonitorList != null && checkMonitorList.size() > 0) {

                                for (CheckMonitor checkMonitor : checkMonitorList) {
                                    String[] strs = new String[]{
                                            checkMonitor.getId(),
                                            checkMonitor.getServiceId(),
                                            checkMonitor.getUmineId(),
                                            checkMonitor.getEquipDepartId(),
                                            checkMonitor.getContent(),
                                            checkMonitor.getTypeId(),
                                            checkMonitor.getOrgId(),
                                            DateToString(checkMonitor.getStartDate()),
                                            DateToString(checkMonitor.getEndDate()),
                                            checkMonitor.getNote(),
                                            IntegerToString(checkMonitor.getIsImport()),
                                            checkMonitor.getCreatorId(),
                                            DateToString(checkMonitor.getCreateDate()),
                                            checkMonitor.getModify(),
                                            DateToString(checkMonitor.getModifyDate())

                                    };
                                    cloumnValues.add(strs);
                                }
                            }


                            String[] cloumnNamescheckMonitor = new String[]{
                                    "id",
                                    "service_id",
                                    "umine_id",
                                    "equip_depart_id",
                                    "content",
                                    "type_id",
                                    "org_id",
                                    "start_date",
                                    "end_date",
                                    "note",
                                    "is_import",
                                    "creator_id",
                                    "create_date",
                                    "modify",
                                    "modify_date"

                            };

                            HSSFWorkbook wbcheckMonitor  = new HSSFWorkbook();
                            wbcheckMonitor = ExportExcel.getHssfWorkBook(wbcheckMonitor, "监督检查信息", cloumnNamescheckMonitor, cloumnValues);

                            List<CheckFileMonitor> checkFileMonitorList = checkFileMonitorMapper.selectAll();

                            cloumnValues = new ArrayList<>();

                            if (checkFileMonitorList != null && checkFileMonitorList.size() > 0) {

                                for (CheckFileMonitor checkFileMonitor : checkFileMonitorList) {
                                    String[] strs = new String[]{
                                            checkFileMonitor.getId(),
                                            checkFileMonitor.getMonitorCheckId(),
                                            checkFileMonitor.getMonitorCheckFileTypeId(),
                                            checkFileMonitor.getFileNo(),
                                            DateToString(checkFileMonitor.getFileDate()),
                                            IntegerToString(checkFileMonitor.getIsImport())
                                    };
                                    cloumnValues.add(strs);
                                }
                            }

                            String[] cloumnNamesFileMonitor = new String[]{
                                    "id",
                                    "monitor_check_id",
                                    "monitor_check_file_type_id",
                                    "file_no",
                                    "file_date",
                                    "is_import"
                            };

                            wbcheckMonitor = ExportExcel.getHssfWorkBook(wbcheckMonitor, "监督检查文件信息", cloumnNamesFileMonitor, cloumnValues);

                            ByteArrayOutputStream baoscheckMonitor = new ByteArrayOutputStream();
                            wbcheckMonitor.write(baoscheckMonitor);
                            listBaos.add(baoscheckMonitor);

                            listName.add("监督检查信息.xls");


                            break;
                        //监督见证信息
                        case "4":

                            cloumnValues = new ArrayList<>();

                           List<WitnessMonitor> witnessMonitorList =  witnessMonitorMapper.selectAll();

                            if (witnessMonitorList != null && witnessMonitorList.size() > 0) {

                                for (WitnessMonitor witnessMonitor : witnessMonitorList) {
                                    String[] strs = new String[]{
                                            witnessMonitor.getId(),
                                            witnessMonitor.getServiceId(),
                                            witnessMonitor.getUmineId(),
                                            witnessMonitor.getEquipDepartId(),
                                            witnessMonitor.getDepartTypeId(),
                                            witnessMonitor.getWitnessObject(),
                                            witnessMonitor.getWitnessItems(),
                                            DateToString(witnessMonitor.getWitnessDate()),
                                            witnessMonitor.getWitnessResult(),
                                            witnessMonitor.getWitnessQuestion(),
                                            witnessMonitor.getReform(),
                                            witnessMonitor.getWitness(),
                                            witnessMonitor.getNote(),
                                            IntegerToString(witnessMonitor.getIsImport()),
                                            witnessMonitor.getCreatorId(),
                                            DateToString(witnessMonitor.getCreateDate()),
                                            witnessMonitor.getModifyId(),
                                            DateToString(witnessMonitor.getModifyDate())
                                    };
                                    cloumnValues.add(strs);
                                }
                            }
                            String[] cloumnNameswitnessMonitor = new String[]{
                                    "id",
                                    "service_id",
                                    "umine_id",
                                    "equip_depart_id",
                                    "depart_type_id",
                                    "witness_object",
                                    "witness_items",
                                    "witness_date",
                                    "witness_result",
                                    "witness_question",
                                    "reform",
                                    "witness",
                                    "note",
                                    "is_import",
                                    "creator_id",
                                    "create_date",
                                    "modify_id",
                                    "modify_date"

                            };

                            HSSFWorkbook wbwitnessMonitor = new HSSFWorkbook();
                            wbwitnessMonitor = ExportExcel.getHssfWorkBook(wbwitnessMonitor, "监督见证信息", cloumnNameswitnessMonitor, cloumnValues);

                            ByteArrayOutputStream baoswitnessMonitor = new ByteArrayOutputStream();
                            wbwitnessMonitor.write(baoswitnessMonitor);
                            listBaos.add(baoswitnessMonitor);

                            listName.add("监督见证信息.xls");


                            break;
                        //核设施安全问题
                        case "5":

                            cloumnValues = new ArrayList<>();

                            List<FacSecurity> facSecurityList = facSecurityMapper.selectAll();

                            if (facSecurityList != null && facSecurityList.size() > 0) {

                                for (FacSecurity facSecurity : facSecurityList) {
                                    String[] strs = new String[]{
                                            facSecurity.getId(),
                                            facSecurity.getServiceId(),
                                            facSecurity.getFacId(),
                                            facSecurity.getFacStatusId(),
                                            facSecurity.getCheckTypeId(),
                                            facSecurity.getContent(),
                                            DateToString(facSecurity.getFindDate()),
                                            facSecurity.getQuestionTypeId(),
                                            facSecurity.getQuestionNatureId(),
                                            facSecurity.getReformStatusId(),
                                            facSecurity.getSuperviseRequire(),
                                            facSecurity.getReformPlan(),
                                            DateToString(facSecurity.getReformCompleteDate()),
                                            facSecurity.getNote(),
                                            IntegerToString(facSecurity.getIsImport()),
                                            facSecurity.getCreatorId(),
                                            DateToString(facSecurity.getCreateDate()),
                                            facSecurity.getModifyId(),
                                            DateToString(facSecurity.getModifyDate())
                                    };
                                    cloumnValues.add(strs);
                                }
                            }
                            String[] cloumnNamesfacSecurity = new String[]{
                                    "id",
                                    "service_id",
                                    "fac_id",
                                    "fac_status_id",
                                    "check_type_id",
                                    "content",
                                    "find_date",
                                    "question_type_id",
                                    "question_nature_id",
                                    "reform_status_id",
                                    " supervise_require",
                                    "reform_plan",
                                    "reform_complete_date",
                                    " note",
                                    " is_import",
                                    " creator_id",
                                    "create_date",
                                    " modify_id",
                                    " modify_date"
                            };

                            HSSFWorkbook wbfacSecurity = new HSSFWorkbook();
                            wbfacSecurity = ExportExcel.getHssfWorkBook(wbfacSecurity, "核设施安全问题", cloumnNamesfacSecurity, cloumnValues);

                            ByteArrayOutputStream baosfacSecurity = new ByteArrayOutputStream();
                            wbfacSecurity.write(baosfacSecurity);
                            listBaos.add(baosfacSecurity);

                            listName.add("核设施安全问题.xls");

                            break;
                    }
                }


               ;

                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode("数据包", "UTF-8") + ".rar");

                int i= 0;
                for (ByteArrayOutputStream byteArrayOutputStream:listBaos
                     ) {
                    ZipUtils.doCompress(byteArrayOutputStream, listName.get(i), outputStream);
                    i++;
                    response.flushBuffer();
                }


            }


        } catch (Exception e) {
            try {
                throw e;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public String DateToString(Date data){

        if(data==null){
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(data);

        return  dateString;
    }

    public String IntegerToString(Integer num){

        if(num==null){
            return "";
        }

        return num.toString();
    }

}
