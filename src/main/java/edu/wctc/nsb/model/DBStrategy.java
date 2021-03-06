/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NStokesBeamon
 */
public interface DBStrategy {

    void closeConnection() throws SQLException;

    void deleteById(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws Exception;
    
    Map<String, Object> findById(String tableName, String primaryKey,
            Object primaryKeyValue);

    List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;

    void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
    void insertRecord(String tableName, List<String> colNames, List<Object> colValues)throws Exception;
    
    void updateRecord(String tableName, List<String> colNames, List colValues, String whereColumn ,Object whereValue)throws Exception;
}
