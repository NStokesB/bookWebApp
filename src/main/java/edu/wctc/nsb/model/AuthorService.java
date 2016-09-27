/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author NStokesBeamon
 */
public class AuthorService {
    private AuthorDAOStrategy dao;

    public AuthorService(AuthorDAOStrategy dao) {
        this.dao = dao;
    }
    
    
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException{
        return dao.getAuthorList();
    }

    public static void main(String[] args) throws Exception{
        AuthorDAOStrategy dao = new AuthorDAO(
        new MySqlDBStrategy(),
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/book?useSSL=false",
            "root", "admin");
        AuthorService service = new AuthorService(dao);
        List<Author> authors = service.getAuthorList();
        System.out.println(authors);
        
    }
}
