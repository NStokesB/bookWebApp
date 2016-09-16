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
import java.util.List;
import java.util.Map;

/**
 *
 * @author NStokesBeamon
 */
public class MySqlDBStrategy  implements DBStrategy {
    private Connection conn;
    
    
    @Override
    public void openConnection(String driverClass, String url, 
            String userName, String password) throws ClassNotFoundException, SQLException {
        
        Class.forName (driverClass);
        conn = DriverManager.getConnection(url,userName, password);
        
    }
    
    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }
    /**
     * Must open and close a connection when using this method.
     * Future optimizations may include changing the return type to an array.
     * It will save on memory. Because ArrayLists have empty slots. 
     * 
     * @param tableName
     * @param maxRecords - Limits result set to this number, or if maxRecords is zero (0) then no limit
     * @return 
     * @throws java.sql.SQLException 
     */
    @Override
    public List<Map<String, Object>> findAllRecords(String tableName, 
            int maxRecords) throws SQLException{
        
        // create MySql statement
        String sql;
        if (maxRecords < 1) {
        
        sql = "select * from " + tableName;
            } else {
        sql = "select * from " + tableName + " limit " + maxRecords;
                    }
        // sorting could happen here
        Statement stmt = conn.createStatement();
        // create result set object.
        ResultSet rs = stmt.executeQuery(sql);
        // get meta data
        ResultSetMetaData rsmd = rs.getMetaData();
        // find out how many columns there are in the table.
        int columnCount = rsmd.getColumnCount();
        // a list of maps to store our records
        List<Map<String, Object>> records = new ArrayList<>();
        
        while ( rs.next()){
            // loop for saving records into our map.
            Map<String, Object> record = new HashMap<>();
                for(int colNo = 1; colNo <= columnCount ; colNo++){
                    // get field of this column
                    Object colData = rs.getObject(colNo);
                    // get columnName
                    String columnName = rsmd.getColumnName(colNo);
                    // put them into a map.
                    record.put(columnName, colData);
                }
                // put our map into our list of maps.
                records.add(record);
        }
                // return this list of maps
        return records;
    }
    
    
    @Override
    public void deleteOneRecord(String tableName, String id) throws ClassNotFoundException, SQLException {
       // Validate the parameters here.
        
       // String sql = "DELETE FROM " + tableName + " WHERE " + column + "=" + value;
        String pKeyColumnName = "";
       // Statement stmt = conn.createStatement();
        
        
        DatabaseMetaData dmd = conn.getMetaData();
        ResultSet rs = null;
      
        // was told this is expensive. Could maybe solve this with an ENUM.
        rs = dmd.getPrimaryKeys(null, null, tableName);
        

        // this works only if there is only one PK... in a parent child relationship I may want to 
        // test for how many PKs I get back... if this is going to work for any table. 
       
            
        while(rs.next()){
        pKeyColumnName = rs.getString("COLUMN_NAME");
       // System.out.println("PK column name is " + pKeyColumnName);
        
        //String sql = "delete from " + tableName + " where " + pKeyColumnName + "=" + id;
        
        String sql2 = "delete from " + tableName + " where " + pKeyColumnName + "=?";
        
        PreparedStatement pstmt = conn.prepareStatement(sql2);
       
        pstmt.setInt(1, Integer.parseInt(id));
        
        pstmt.executeUpdate(); 
        }

    }
    
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        System.out.println(db.findAllRecords("author", 0).toString());
        db.deleteOneRecord("author", "2");
        System.out.println(db.findAllRecords("author", 0).toString());
        db.closeConnection();
        
    }
    
}
