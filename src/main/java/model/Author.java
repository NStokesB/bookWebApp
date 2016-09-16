/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author NStokesBeamon
 */
public class Author {
    private int authorId;
    private String authorName;
    private Date dateAdded;
    private final String ERROR_MSG = "There is a problem with the DataBase :(";
    
    public Author() {
    }

    public Author(int authorId) throws IllegalArgumentException {
          if(authorId < 1000){
            throw new IllegalArgumentException(ERROR_MSG);
        }
        
        this.authorId = authorId;
    }
    
// This constructor is to simplify the hard coding of this list later
    
    public Author(int authorId, String authorName) throws IllegalArgumentException {
        if(authorId < 1000 || authorName.isEmpty()){
            throw new IllegalArgumentException(ERROR_MSG);
        }
        
        
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateAdded = new Date();
    }
    
    
    public int getAuthorId()  {
        return authorId;
    }

    public void setAuthorId(int authorId) throws IllegalArgumentException {
        if(authorId < 1000){
            throw new IllegalArgumentException(ERROR_MSG);
        }
        
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) throws IllegalArgumentException {
        if(authorName.isEmpty()){
            throw new IllegalArgumentException(ERROR_MSG);
        }
        this.authorName = authorName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) throws IllegalArgumentException {
        if(dateAdded == null){
            throw new IllegalArgumentException(ERROR_MSG);
        }
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.authorId;
        hash = 79 * hash + Objects.hashCode(this.authorName);
        hash = 79 * hash + Objects.hashCode(this.dateAdded);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        if (!Objects.equals(this.authorName, other.authorName)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", authorName=" + authorName + ", dateAdded=" + dateAdded + '}';
    }
    
}
