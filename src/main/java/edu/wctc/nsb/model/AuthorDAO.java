/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NStokesBeamon
 */
public class AuthorDAO implements AuthorDAOStrategy {
    private DBStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
 
    public AuthorDAO(DBStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    
    @Override
    public List<Author> getAuthorList() 
            throws ClassNotFoundException, SQLException {
        
        db.openConnection(driverClass, url, userName, password);
        List<Map<String,Object>> records = db.findAllRecords("author", 50);
        List<Author> authors = new ArrayList<>();
        for(Map<String,Object> rec : records) {
            Author author = new Author();
            int id = Integer.parseInt(rec.get("author_id").toString());
            author.setAuthorId(id);
            String name = rec.get("author_name").toString();
            author.setAuthorName(name != null ? name : "");
            Date date = (Date)rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
            
        }
        db.closeConnection();
        return authors;
    }
    
     public void deleteAuthorById(String id){
        db.openConnection(driverClass, url, userName, password);
        Integer primaryKeyValue = Integer.parseInt(id);
        db.deleteById("author", "author_id", primaryKeyValue);
        db.closeConnection();
    }

    public DBStrategy getDb() {
        return db;
    }

    public void setDb(DBStrategy db) {
        this.db = db;
    }
    
    public static void main(String[] args)throws Exception {
        AuthorDAOStrategy dao = new AuthorDAO(
                new MySqlDBStrategy(), 
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book?useSSL=false",
                "root", "admin");
        
        List<Author> authors = dao.getAuthorList();
        System.out.println(authors);
        
    }
}
