package com.rainbow.dataMigration.service.impl;

import com.rainbow.attachment.dao.FileInfoMapper;
import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.check.dao.FacCheckMapper;
import com.rainbow.check.dao.FacFileCheckMapper;
import com.rainbow.check.domain.FacCheck;
import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.config.RainbowProperties;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.*;
import com.rainbow.dataMigration.dao.DataMigrationMapper;
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
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    @Autowired
    private RainbowProperties rainbowProperties;

    @Autowired
    DataMigrationMapper dataMigrationMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    @SystemLog(description="数据迁移导出")
    public  void exportData(String type,HttpServletResponse response) {

        String[] tables = type.split(",");

        List<String[]> cloumnValues = new ArrayList<>();
        List<FileInfo> fileInfoList = new ArrayList<>();

        Map<String,String> map = new HashMap<>();
        map.put("type","0");

        //创建临时文件
        String path=rainbowProperties.getUploadFolder()+"/temp";
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdirs();
        }else{
            delFile(file1);
            file1.mkdirs();
        }

        try  {
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
                                            "2",
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
                                            "2",
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

                            FileOutputStream output=new FileOutputStream(path+"/checkfac.xls");
                            wb.write(output);
                            output.close();

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
                                            "2",
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

                            FileOutputStream outputDaily=new FileOutputStream(path+"/monitordaily.xls");
                            wbDaily.write(outputDaily);
                            outputDaily.close();

                            //附件
                            map.put("tablename","monitor_daily");
                            List<FileInfo> fileInfoMonitorDailyList=fileInfoMapper.getFileInfoByTableName(map);
                            if(fileInfoMonitorDailyList!=null&&fileInfoMonitorDailyList.size()>0){
                                fileInfoList.addAll(fileInfoMonitorDailyList);
                            }

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
                                            "2",
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
                                            "2"
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

                            FileOutputStream outputCheckMonitor=new FileOutputStream(path+"/monitorcheck.xls");
                            wbcheckMonitor.write(outputCheckMonitor);
                            outputCheckMonitor.close();

                            //附件
                            map.put("tablename","monitor_check");
                            List<FileInfo> fileInfoCheckMonitorList=fileInfoMapper.getFileInfoByTableName(map);
                            if(fileInfoCheckMonitorList!=null&&fileInfoCheckMonitorList.size()>0){
                                fileInfoList.addAll(fileInfoCheckMonitorList);
                            }

                            map.put("tablename","monitor_check_file");
                            List<FileInfo> fileInfoCheckFileMonitorList=fileInfoMapper.getFileInfoByTableName(map);
                            if(fileInfoCheckFileMonitorList!=null&&fileInfoCheckFileMonitorList.size()>0){
                                fileInfoList.addAll(fileInfoCheckFileMonitorList);
                            }
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
                                            "2",
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

                            FileOutputStream outputWitnessMonitor=new FileOutputStream(path+"/monitorwitness.xls");
                            wbwitnessMonitor.write(outputWitnessMonitor);
                            outputWitnessMonitor.close();

                            //附件
                            map.put("tablename","monitor_witness");
                            List<FileInfo> fileInfoWitnessMonitorList=fileInfoMapper.getFileInfoByTableName(map);
                            if(fileInfoWitnessMonitorList!=null&&fileInfoWitnessMonitorList.size()>0){
                                fileInfoList.addAll(fileInfoWitnessMonitorList);
                            }

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
                                            "2",
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

                            FileOutputStream outputWbfacSecurity=new FileOutputStream(path+"/securityfac.xls");
                            wbfacSecurity.write(outputWbfacSecurity);
                            outputWbfacSecurity.close();

                            //附件
                            map.put("tablename","security_fac");
                            List<FileInfo> fileInfoFacSecurityList=fileInfoMapper.getFileInfoByTableName(map);
                            if(fileInfoFacSecurityList!=null&&fileInfoFacSecurityList.size()>0){
                                fileInfoList.addAll(fileInfoFacSecurityList);
                            }

                            break;
                    }
                }

                cloumnValues = new ArrayList<>();
                //附件
                if(fileInfoList.size()>0){

                    for (FileInfo fileInfo : fileInfoList) {
                        String[] strs = new String[]{
                            fileInfo.getFileinfoId(),
                            fileInfo.getFileinfoRefId(),
                            fileInfo.getFileinfoFileType(),
                            fileInfo.getFileinfoServerFileName(),
                                fileInfo.getFileinfoClientFileName(),
                                fileInfo.getFileinfoServerPath(),
                                fileInfo.getFileinfoUploadUserId(),
                                DateToString(fileInfo.getFileinfoUploadDate()),
                                fileInfo.getFileinfoContent(),
                                "2"
                        };

                        File file = new File(fileInfo.getFileinfoServerPath());
                        if(file.exists()){
                           FileOutputStream fileO = new FileOutputStream(path+"/"+fileInfo.getFileinfoServerFileName());
                            byte[] xlsBytes = File2byte(fileInfo.getFileinfoServerPath());
                            fileO.write(xlsBytes);
                            fileO.close();

                        }
                        cloumnValues.add(strs);
                    }

                    String[] cloumnNamesfacSecurity = new String[]{
                            "fileinfo_id",
                            "fileinfo_ref_id",
                            "fileinfo_file_type",
                            "fileinfo_server_file_name",
                            "fileinfo_client_file_name",
                            "fileinfo_server_path",
                            "fileinfo_upload_user_id",
                            "fileinfo_upload_date",
                            "fileinfo_content",
                            "is_import"
                    };

                    HSSFWorkbook wbFile = new HSSFWorkbook();
                    wbFile = ExportExcel.getHssfWorkBook(wbFile, "附件", cloumnNamesfacSecurity, cloumnValues);

                    FileOutputStream outputFile=new FileOutputStream(path+"/fileinfo.xls");
                    wbFile.write(outputFile);
                    outputFile.close();
                }

               ;

                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode("datas", "UTF-8") + ".jm");

                String zipPath =   CompressUtil.zip(path,"haqsjk.2019");

                OutputStream out = response.getOutputStream();
                byte[] xlsBytes = File2byte(zipPath);
                out.write(xlsBytes);
                out.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    @SystemLog(description="数据迁移导入")
    public ResponseBo importData(HttpServletRequest request) {

        try {
            Multipart part = new Multipart();
            //获取前端传过来的file
            MultipartFile file = part.getUploadFile(request);

            String webInfPath = rainbowProperties.getUploadFolder();

            File dirs = new File(webInfPath + "/zips");
            if (!dirs.exists()) {
                dirs.mkdirs();
            }else{
                delFile(dirs);
                dirs.mkdirs();
            }

            String path = webInfPath + "/zips/" +file.getOriginalFilename();
            File zipFile = new File(path);
            file.transferTo(zipFile);

            File[] files =CompressUtil.unzip(path,"haqsjk.2019");

            if(files.length>0){

               List<FileInfo> fileInfoList= new ArrayList<>();
               List<File> fileList = new ArrayList<>();

                for (File f:files) {

                    String fileNameNow = f.getName().substring(f.getName().lastIndexOf("\\")+1);
                    switch (fileNameNow){
                        case "checkfac.xls":

                            deleteHistory("check_fac");
                            deleteHistory("check_fac_file");
                            FileInputStream inputStream = new FileInputStream(f);

                            List<FacCheck> list = ExcelHelper.convertToList(FacCheck.class, fileNameNow, inputStream, 2, 12,0);
                            if(list!=null&&list.size()>0){
                                for (FacCheck facCheck:list) {
                                    facCheckMapper.insert(facCheck);
                                }
                            }

                            FileInputStream inputStream1 = new FileInputStream(f);

                            List<FacFileCheck> facFileCheckList = ExcelHelper.convertToList(FacFileCheck.class, fileNameNow, inputStream1, 2, 7,1);
                            if(facFileCheckList!=null&&facFileCheckList.size()>0){

                                for (FacFileCheck facFileCheck:facFileCheckList) {
                                    facFileCheckMapper.insert(facFileCheck);
                                }
                            }

                        break;
                        case "monitordaily.xls":

                            deleteHistory("monitor_daily");

                            FileInputStream inputStream2 = new FileInputStream(f);
                            List<DailyMonitor> dailyMonitorList = ExcelHelper.convertToList(DailyMonitor.class, fileNameNow, inputStream2, 2, 14,0);
                            if(dailyMonitorList!=null&&dailyMonitorList.size()>0){

                                for (DailyMonitor dailyMonitor:dailyMonitorList) {
                                    dailyMonitorMapper.insert(dailyMonitor);
                                }

                            }

                            break;

                        case "monitorcheck.xls":
                            FileInputStream inputStream3 = new FileInputStream(f);
                            deleteHistory("monitor_check");
                            deleteHistory("monitor_check_file");

                            List<CheckMonitor>  checkMonitorList =ExcelHelper.convertToList(CheckMonitor.class,fileNameNow, inputStream3, 2, 15,0);
                            if(checkMonitorList!=null&&checkMonitorList.size()>0){

                                for (CheckMonitor checkMonitor:checkMonitorList  ) {
                                    checkMonitorMapper.insert(checkMonitor);
                                }

                            }

                            FileInputStream inputStream31 = new FileInputStream(f);

                            List<CheckFileMonitor> checkFileMonitorList = ExcelHelper.convertToList(CheckFileMonitor.class, fileNameNow, inputStream31, 2, 6,1);
                            if(checkFileMonitorList!=null&&checkFileMonitorList.size()>0){

                                for (CheckFileMonitor checkFileMonitor:checkFileMonitorList) {
                                    checkFileMonitorMapper.insert(checkFileMonitor);
                                }

                            }

                            break;
                        case "monitorwitness.xls":
                            FileInputStream inputStream4 = new FileInputStream(f);

                            deleteHistory("monitor_witness");

                            List<WitnessMonitor> witnessMonitorList =ExcelHelper.convertToList(WitnessMonitor.class, fileNameNow, inputStream4, 2, 18,1);
                            if(witnessMonitorList!=null&&witnessMonitorList.size()>0){

                                for (WitnessMonitor witnessMonitor:witnessMonitorList ) {
                                    witnessMonitorMapper.insert(witnessMonitor);
                                }
                            }
                            break;

                        case "securityfac.xls":
                            FileInputStream inputStream5 = new FileInputStream(f);

                            deleteHistory("security_fac");
                            List<FacSecurity> facSecurityList = ExcelHelper.convertToList(FacSecurity.class, fileNameNow, inputStream5, 2, 19,0);
                            if(facSecurityList!=null&&facSecurityList.size()>0){

                                for (FacSecurity facSecurity:facSecurityList ) {
                                    facSecurityMapper.insert(facSecurity);
                                }

                            }

                            break;
                        case "fileinfo.xls":

                           FileInputStream inputStream6 = new FileInputStream(f);
                         fileInfoList = ExcelHelper.convertToList(FileInfo.class, fileNameNow, inputStream6, 2, 10,0);
                            break;
                        default:

                             if(fileNameNow.indexOf("datas")==-1){

                                 fileList.add(f);

                            }

                            break;
                    }
                }


                if(fileInfoList!=null&&fileInfoList.size()>0){

                    for (FileInfo fileInfo:fileInfoList ) {
                        String storageFolder = fileInfoService.GetFileStorageFolder(fileInfo.getFileinfoId());
                        fileInfo.setFileinfoServerPath(storageFolder+fileInfo.getFileinfoServerFileName());
                        fileInfoMapper.insert(fileInfo);
                    }

                }

                if(fileList.size()>0){
                    for (File f1:fileList ) {

                        String fileNameNow = f1.getName().substring(f1.getName().lastIndexOf("\\")+1);
                        // 保存文件
                        String id = fileNameNow.substring(0,fileNameNow.indexOf("."));
                        String storageFolder = fileInfoService.GetFileStorageFolder(id);
                        String fileSavePath = storageFolder + fileNameNow;
                        File fileDir = new File(storageFolder);
                        if (!fileDir.exists()) {
                            fileDir.mkdirs();
                        }
                        File file1 = new File(fileSavePath);
                        f1.renameTo(file1);
                    }
                }
            }


        } catch (IOException e) {
            ResponseBo.error(e.getMessage());
        } catch (ZipException e) {
            ResponseBo.error(e.getMessage());
        }

        return  ResponseBo.ok();
    }

    public void deleteHistory(String type){

        Map<String,String> map =new HashMap<>();
        map.put("tablename",type);
        map.put("type","1");

        List<FileInfo> list = fileInfoMapper.getFileInfoByTableName(map);
        if(list!=null&&list.size()>0){
            for (FileInfo fileInfo:list) {
                File file = new File(fileInfo.getFileinfoServerPath());
                if(file.exists()){
                    file.delete();
                }
                fileInfoMapper.delete(fileInfo);
            }
        }

        dataMigrationMapper.deleteHistory(map);

    }

    public  byte[] File2byte(String tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){

        }catch (IOException e){

        }
        return buffer;
    }

    public boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }
}
