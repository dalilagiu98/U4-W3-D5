package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entities.BibliographicalElement;
import org.example.entities.Loan;
import org.example.exceptions.NotFoundException;

import java.util.List;

public class LoansDAO {

    //ENTITY MANAGER:
    private final EntityManager em;

    public LoansDAO(EntityManager em) {
        this.em = em;
    }

    //METHODS:

    public void save(Loan loan) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(loan);
            transaction.commit();
            System.out.println("Item " + loan.getBibliographicalElement().getTitle() + " loaned to " + loan.getUser().getFirstName() + " " + loan.getUser().getLastName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Loan findById(long id) {
        Loan loan = em.find(Loan.class, id);
        if(loan == null) {
            throw new NotFoundException(id);
        }
        return loan;
    }

    public void deleteById (long id){
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Loan found = em.find(Loan.class, id);
            if(found != null) {
                em.remove(found);
                transaction.commit();
                System.out.println("Loan deleted!");
            } else {
                System.out.println("Loan not found!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Loan> findBorrowedItemByCardNumber(long cardNumber) {
        TypedQuery<Loan> query = em.createNamedQuery("findBorrowedItemByCardNumber", Loan.class);
        query.setParameter("cardNumber", cardNumber);
        return query.getResultList();
    }

    public List<Loan> findExpiredNotReturned () {
        TypedQuery<Loan> query = em.createQuery("findExpiredNotReturned", Loan.class);
        return query.getResultList();
    }
}
