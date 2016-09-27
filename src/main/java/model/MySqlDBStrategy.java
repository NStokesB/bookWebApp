/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NStokesBeamon
 */
public class MySqlDBStrategy implements DBStrategy {
     private Connection conn;
   
    @Override
    public void openConnection(String driverClass, String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws SQLException {

        conn.close();
    }
    
    

    @Override
    public List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " Limit" + maxRecords;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Map<String,Object>> records = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        while (rs.next()) {
            Map<String, Object> record = new LinkedHashMap<>();
            for (int i = 0; i < colCount; i++) {
                String colName = rsmd.getColumnName(i + 1);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
            records.add(record);

        }
        return records;
    }
    
    @Override
     public void deleteOneRecord(String tableName, String id) throws ClassNotFoundException, SQLException {
 
        String pKeyColumnName = "";
        DatabaseMetaData dmd = conn.getMetaData();
        ResultSet rs = null;
           
        rs = dmd.getPrimaryKeys(null, null, tableName);
                   
        while(rs.next()){
        pKeyColumnName = rs.getString("COLUMN_NAME");
       
        
        String sql2 = "delete from " + tableName + " where " + pKeyColumnName + "=?";
        
        PreparedStatement pstmt = conn.prepareStatement(sql2);
       
        pstmt.setInt(1, Integer.parseInt(id));
        
        pstmt.executeUpdate(); 
        }

    }
    
    @Override
    public final Map<String, Object> findById(String tableName, String primaryKey,
            Object primaryKeyValue) {

        String sql = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = ?";
        PreparedStatement stmt = null;
        final Map<String, Object> record = new HashMap();

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, primaryKeyValue);
            ResultSet rs = stmt.executeQuery();
            final ResultSetMetaData metaData = rs.getMetaData();
            final int fields = metaData.getColumnCount();

            
            if (rs.next()) {
                for (int i = 1; i <= fields; i++) {
                    record.put(metaData.getColumnName(i), rs.getObject(i));
                }
            }
            
        } catch (SQLException e) {
           
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
             
            }
        } 

        return record;
    }

    public static void main(String[] args) throws Exception {
        MySqlDBStrategy db = new MySqlDBStrategy();

        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book?useSSL=false",
                "root", "admin");
        
        db.deleteOneRecord("author", "1");
        List<Map<String,Object>> records = db.findAllRecords("author", 50);
        System.out.println(records);
        db.closeConnection();
    }

}
