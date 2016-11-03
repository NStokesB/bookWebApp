/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.ejb;

import edu.wctc.nsb.model.Author;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author NStokesBeamon
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
    public List<Author> findByName(String name){
        List<Author> authorList = null;
        
//        String jpql = "selecta from Author where a.authorName =?1";
//        TypedQuery<Author> query = getEntityManager().createQuery(jpql, Author.class);
        
        TypedQuery<Author> query = getEntityManager().createNamedQuery("findByName", Author.class);
        query.setParameter(1, name);
        authorList = query.getResultList();
        
        return authorList;
    }
    
    public void deleteById(String id) {
        Author author = this.find(new Integer(id));
        this.remove(author);
    }
    
      public void saveOrUpdate(String id, String name) {
        Author author = new Author();
        if(id == null) {
            // must be a new record
            author.setAuthorName(name);
            author.setDateAdded(new Date());
        } else {
            // modify record
            author.setAuthorId(new Integer(id));
            author.setAuthorName(name);
        }
        this.getEntityManager().merge(author);
    }
    
}
