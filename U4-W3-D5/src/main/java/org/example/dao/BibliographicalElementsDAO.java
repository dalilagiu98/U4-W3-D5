package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entities.BibliographicalElement;
import org.example.entities.Book;
import org.example.exceptions.NotFoundException;

import java.util.List;

public class BibliographicalElementsDAO {

    //ENTITY MANAGER:
   private final EntityManager em;

    public BibliographicalElementsDAO(EntityManager em) {
        this.em = em;
    }

    //DAO METHODS:

    public void save (BibliographicalElement bibliographicalElement) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(bibliographicalElement);
            transaction.commit();
            System.out.println("Bibliographical element saved!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public BibliographicalElement findById (long id){
        BibliographicalElement bibliographicalElement = em.find(BibliographicalElement.class, id);
        if (bibliographicalElement == null){
            throw new NotFoundException(id);
        }
        return bibliographicalElement;
    }

    public void deleteById(long id) {
        try {
           EntityTransaction transaction = em.getTransaction();
           transaction.begin();
           BibliographicalElement found = em.find(BibliographicalElement.class, id);
           if(found != null) {
               em.remove(found);
               transaction.commit();
               System.out.println("Bibliographical element deleted!");
           } else {
               System.out.println("Bibliographical element not found!");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<BibliographicalElement> findByPublicationYear (int year){
        TypedQuery<BibliographicalElement> query = em.createNamedQuery("findByYear", BibliographicalElement.class);
        query.setParameter("publicationYear", year);
        return query.getResultList();
    }

    public List<Book> findByAuthor (String author) {
        TypedQuery<Book> query = em.createNamedQuery("findByAuthor", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    public List<BibliographicalElement> findByTitle(String title){
        TypedQuery<BibliographicalElement> query = em.createQuery("findByTitle", BibliographicalElement.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }
}
