package com.rainbow.dataMigration.dao;

import java.util.Map;

/**
 * Created by 13260 on 2019/8/31.
 */
public interface DataMigrationMapper {

    void deleteHistory(Map<String,String> map);

}
