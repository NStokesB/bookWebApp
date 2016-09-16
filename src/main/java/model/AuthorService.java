/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author NStokesBeamon
 */
public class AuthorService {
//        Author kerouac = new Author(1000, "Jack Kerouac", new Date());
//        Author king = new Author(1001, "Stephen King", new Date());
//        Author loftus = new Author(1002, "John w. Loftus", new Date());
    
    private List<Author> authorList = new ArrayList<>(Arrays.asList
        (new Author(1000, "J.K. Rowling"), new Author(1001, "Stephen King"), new Author(1002, "Stephenie Meyer")));
        
            

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) throws IllegalArgumentException{
        if( authorList.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.authorList = authorList;
    }

    public List returnAllAuthors(){

        return authorList;
    }
    
    // test
//     public static void main(String[] args) {
//         AuthorService aServ = new AuthorService();
//         List<Author> List = aServ.returnAllAuthors();
//         for(Author a : List)
//         System.out.println(a.toString());
//     }
}
