package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@NamedQuery(name = "findBorrowedItemByCardNumber", query = "SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber AND l.effectiveReturnDate IS NULL" )
@NamedQuery(name = "findExpiredNotReturned", query = "SELECT l FROM Loan l WHERE l.loanReturnDate < CURRENT_DATE AND l.effectiveReturnDate IS NULL")
public class Loan {
    //ATTRIBUTES LIST:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    @OneToOne
    private BibliographicalElement bibliographicalElement;
    private LocalDate loanStartDate;
    private LocalDate loanReturnDate;
    private LocalDate effectiveReturnDate;

    //CONSTRUCTOR:
    public Loan(){}
    public Loan(User user, BibliographicalElement bibliographicalElement){
        this.user = user;
        this.bibliographicalElement = bibliographicalElement;
        this.loanStartDate = LocalDate.now();
        this.loanReturnDate = LocalDate.now().plusDays(30);
        this.effectiveReturnDate = null;
    }
    public Loan(User user, BibliographicalElement bibliographicalElement, LocalDate effectiveReturnDate){
        this.user = user;
        this.bibliographicalElement = bibliographicalElement;
        this.loanStartDate = generateRandomDate();
        this.loanReturnDate = this.loanStartDate.plusDays(30);
        this.effectiveReturnDate = effectiveReturnDate;
    }

    //METHODS:
    public User getUser() {
        return user;
    }

    public BibliographicalElement getBibliographicalElement() {
        return bibliographicalElement;
    }

    public void setEffectiveReturnDate(LocalDate effectiveReturnDate) {
        this.effectiveReturnDate = effectiveReturnDate;
    }

    public static LocalDate generateRandomDate() {
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "user=" + user +
                ", bibliographicalElement=" + bibliographicalElement +
                ", loanStartDate=" + loanStartDate +
                ", loanReturnDate=" + loanReturnDate +
                ", effectiveReturnDate=" + effectiveReturnDate +
                '}';
    }
}
