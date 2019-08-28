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
import com.rainbow.monitor.dao.DailyMonitorMapper;
import com.rainbow.monitor.domain.DailyMonitor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    FileInfoMapper fileInfoMapper;

    @Override
    public  void exportData(String type,HttpServletResponse response) {

        String[] tables = type.split(",");

        List<String[]> cloumnValues = new ArrayList<>();
        List<FileInfo> fileInfoList = new ArrayList<>();
        List<ByteArrayOutputStream> listBaos = new ArrayList<>();
        List<String> listName = new ArrayList<>();

        try (
                ZipOutputStream outputStream = new ZipOutputStream(response.getOutputStream())) {

            if (tables != null && tables.length > 0) {

                for (String str : tables) {
                    switch (str) {
                        //核设施审评
                        case "1":

                            List<FacCheck> facCheckList = facCheckMapper.selectAll();
                            if (facCheckList != null && facCheckList.size() > 0) {

                                for (FacCheck facCheck : facCheckList) {
                                    String[] strs = new String[]{
                                            facCheck.getServiceId(),
                                            facCheck.getFacId(),
                                            facCheck.getTypeId(),
                                            facCheck.getStageId(),
                                            DateToString(facCheck.getCheckDate()),
                                            facCheck.getNote(),
                                            facCheck.getIsImport().toString(),
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
                                            facFileCheck.getCheckFacId(),
                                            facFileCheck.getFileName(),
                                            facFileCheck.getFacCheckFileTypeId(),
                                            DateToString(facFileCheck.getFileDate()),
                                            facFileCheck.getFileNo(),
                                            facFileCheck.getIsImport().toString()
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
                            fileInfoList = fileInfoMapper.getFileInfoByTableName("check_fac_file");


                            break;
                        //日常监督信息
                        case "2":

                            List<DailyMonitor> dailyMonitorList = dailyMonitorMapper.selectAll();

                            if (dailyMonitorList != null && dailyMonitorList.size() > 0) {

                                for (DailyMonitor dilyMonitor : dailyMonitorList) {
                                    String[] strs = new String[]{
                                            dilyMonitor.getServiceId(),
                                            dilyMonitor.getFacId(),
                                            dilyMonitor.getStatusId(),
                                            dilyMonitor.getOrgId(),
                                            dilyMonitor.getFileTypeId(),
                                            dilyMonitor.getFileName(),
                                            DateToString(dilyMonitor.getFileDate()),
                                            dilyMonitor.getNote(),
                                            dilyMonitor.getIsImport().toString(),
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

                            break;
                        //监督检查信息
                        case "3":
                            break;
                        //监督见证信息
                        case "4":
                            break;
                        //核设施安全问题
                        case "5":
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

        }
    }

    public String DateToString(Date data){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(data);

        return  dateString;
    }

}
