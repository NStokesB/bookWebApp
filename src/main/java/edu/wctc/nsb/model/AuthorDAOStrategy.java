/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.model;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author NStokesBeamon
 */
public interface AuthorDAOStrategy {

    List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    
    void initDao(String driverClass, String url, String userName, String password);
    
    void deleteAuthorById(String id)throws Exception;
    
    void insertNewRecord(List<Object> colValues)throws Exception;
    
    void updateRecords(Integer authorId, String authorName) throws SQLException, Exception;
    
    Author findAuthorById(Integer authorId)throws Exception;
    
    void initDao(DataSource ds) throws SQLException;
    
}
