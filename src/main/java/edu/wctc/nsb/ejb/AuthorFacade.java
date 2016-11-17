/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.ejb;

import edu.wctc.nsb.model.Author;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
         public List<Author> findByName(String name) {
        String jpql = "select a from Author where a.authorName = ?1";
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter(1, name);
        return q.getResultList();
    }
         
       public void deleteById(String id) {
        Author author = this.find(new Integer(id));
        this.remove(author);
    }     
    
}
