package org.example;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.BibliographicalElementsDAO;
import org.example.dao.LoansDAO;
import org.example.dao.UsersDAO;
import org.example.entities.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d5");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker(Locale.ITALY);
        BibliographicalElementsDAO bibliographicalElementsDAO = new BibliographicalElementsDAO(em);
        LoansDAO loansDAO = new LoansDAO(em);
        UsersDAO usersDAO = new UsersDAO(em);
        Random random = new Random();


        try {
            //----------------------- CREATION USERS AND BIBLIOGRAPHICAL ELEMENTS -------------------------
/*
        for (int i = 0; i < 20; i++) {
            usersDAO.save(new User(faker.name().firstName(), faker.name().lastName(), LocalDate.of(random.nextInt(1920,2020), random.nextInt(1,13), random.nextInt(1,31))));
        }

        for(int i = 0; i < 40; i++) {
            bibliographicalElementsDAO.save(new Book(faker.code().isbn10(true), faker.book().title(), random.nextInt(10, 1000), faker.book().author(), faker.book().genre() ));
        }

        for(int i = 0; i < 30; i++) {
            bibliographicalElementsDAO.save(new Magazine(faker.code().isbn10(true), faker.book().title(), random.nextInt(10,200)));
        }
*/

            //---------------------------------- CREATION OF LOANS ----------------------------------
            User user1 = usersDAO.findById(4);
            User user2 = usersDAO.findById(6);
            BibliographicalElement element1 = bibliographicalElementsDAO.findById("0-01-354243-5");
            BibliographicalElement element2 = bibliographicalElementsDAO.findById("0-07-704248-4");
            BibliographicalElement element3 = bibliographicalElementsDAO.findById("0-7446-9842-1");
            BibliographicalElement element4 = bibliographicalElementsDAO.findById("0-86679-161-2");
            BibliographicalElement element5 = bibliographicalElementsDAO.findById("0-87730-062-3");
            BibliographicalElement element6 = bibliographicalElementsDAO.findById("0-900309-98-9");
            BibliographicalElement element7 = bibliographicalElementsDAO.findById("0-921017-14-6");
            BibliographicalElement element8 = bibliographicalElementsDAO.findById("1-7813-0481-5");


            Loan loan1 = new Loan(user1, element1);
            Loan loan2 = new Loan(user1, element2, LocalDate.now().minusDays(21));
            Loan loan3 = new Loan(user1, element3);
            Loan loan4 = new Loan(user2, element4);
            Loan loan5 = new Loan(user2, element5, LocalDate.now().minusDays(12));
            Loan loan6 = new Loan(user2, element6, LocalDate.now().minusDays(3));
            Loan loan7 = new Loan(user1, element7, LocalDate.now().minusDays(21));
            Loan loan8 = new Loan(user1, element8);

/*        loansDAO.save(loan1);
        loansDAO.save(loan2);
        loansDAO.save(loan3);
        loansDAO.save(loan4);
        loansDAO.save(loan5);
        loansDAO.save(loan6);
        loansDAO.save(loan7);
        loansDAO.save(loan8);*/

            //---------------------------------- METHODS OF DAO ----------------------------------
            System.out.println("------ ADDING ELEMENT TO CATALOGUE--------");
            BibliographicalElement newItem = new Book(faker.code().isbn10(true), "Alice in Wonderland", 356, "Lewis Carroll", "Fantasy");
            bibliographicalElementsDAO.save(newItem);

//        System.out.println("------ REMOVE ELEMENT BY ISBN --------");
//        bibliographicalElementsDAO.deleteById("1-89172-193-3");

            System.out.println("------ SEARCH ELEMENT BY ISBN --------");
            System.out.println(bibliographicalElementsDAO.findById("1-9973620-8-2"));

            System.out.println("------ SEARCH ELEMENT BY PUBLICATION YEAR --------");
            bibliographicalElementsDAO.findByPublicationYear(1712).forEach(System.out::println);

            System.out.println("------ SEARCH ELEMENT BY AUTHOR --------");
            bibliographicalElementsDAO.findByAuthor("Vera Negri").forEach(System.out::println);

            System.out.println("------ SEARCH ELEMENT BY TITLE (OR A PART OF THIS) --------");
            bibliographicalElementsDAO.findByTitle("The").forEach(System.out::println);

            System.out.println("------ SEARCH ELEMENT CURRENTLY ON LOAN BY A CARD NUMBER --------");
            loansDAO.findBorrowedItemByCardNumber(4).forEach(System.out::println);

            System.out.println("------ SEARCH ALL LOANS EXPIRED AND NOT YET RETURNED --------");
            loansDAO.findExpiredNotReturned().forEach(System.out::println);
        } finally {
            em.close();
            emf.close();
        }
    }
}
