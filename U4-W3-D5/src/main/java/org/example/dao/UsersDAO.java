package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entities.User;

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
}
