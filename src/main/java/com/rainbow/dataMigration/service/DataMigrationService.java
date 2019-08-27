package com.rainbow.dataMigration.service;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 13260 on 2019/8/27.
 */
public interface DataMigrationService {

   void exportData(String type,HttpServletResponse response);

}
