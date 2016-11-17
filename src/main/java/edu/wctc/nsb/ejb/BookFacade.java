/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.ejb;

import edu.wctc.nsb.model.Book;
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
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
       public List<Book> findByAuthorId(int Id){
        String jpql = "select b from Book b where b.authorId.authorId = ?1";
        TypedQuery<Book> q = getEntityManager().createQuery(jpql, Book.class);
        q.setParameter(1, Id);
        return q.getResultList();
    }
    
    public List<Book> findByTitle(String title) {
        String jpql = "select b from Book b where b.title = ?1";
        TypedQuery<Book> q = getEntityManager().createQuery(jpql, Book.class);
        q.setParameter(1, title);
        return q.getResultList();
    }
}
