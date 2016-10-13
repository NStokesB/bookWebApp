/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author NStokesBeamon
 */
@SessionScoped
public class AuthorService implements Serializable {
    @Inject
    private AuthorDAOStrategy dao;

    public AuthorService() {
       
    }

    public AuthorDAOStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDAOStrategy dao) {
        this.dao = dao;
    }
    
    public void deleteByAuthorId(String id)throws SQLException, ClassNotFoundException, Exception {
        dao.deleteAuthorById(id);
    }
    
    public void editAuthorRecord(String authorId, String authorName) throws Exception{
         Integer id = null;
        if (authorId == null || authorId.isEmpty()) {
            id = null;
        } else {
            id = Integer.parseInt(authorId);
        }
        dao.updateRecords(id, authorName);
               
    }
    
    public Author findAuthorById(Integer authorId) throws Exception{
        return dao.findAuthorById(authorId);
    }
    
    
    public void createNewRecordInTable(String authorName) throws SQLException, ClassNotFoundException, Exception {
        List<Object> colValues = new ArrayList<>();
        colValues.add(authorName);
        colValues.add(new Date());
        dao.insertNewRecord(colValues);        
     }
    
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException{
        return dao.getAuthorList();
    }

//    public static void main(String[] args) throws Exception{
//        AuthorDAOStrategy dao = new AuthorDAO(
//        new MySqlDBStrategy(),
//            "com.mysql.jdbc.Driver",
//            "jdbc:mysql://localhost:3306/book?useSSL=false",
//            "root", "admin");
//        AuthorService service = new AuthorService(dao);
//        List<Author> authors = service.getAuthorList();
//        System.out.println(authors);
//        
//    }
}
