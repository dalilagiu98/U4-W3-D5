package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entities.Loan;
import org.example.entities.User;
import org.example.exceptions.NotFoundException;

public class UsersDAO {
    //ENTITY MANAGER:
    private final EntityManager em;

    public UsersDAO(EntityManager em) {
        this.em = em;
    }

    //METHODS:
    public void save (User user) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(user);
            transaction.commit();
            System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User findById(long id) {
        User user = em.find(User.class, id);
        if(user == null) {
            throw new NotFoundException(id);
        }
        return user;
    }
}
