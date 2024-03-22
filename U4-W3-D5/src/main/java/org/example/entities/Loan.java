package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
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
    public Loan(User user, BibliographicalElement bibliographicalElement, LocalDate effectiveReturnDate){
        this.user = user;
        this.bibliographicalElement = bibliographicalElement;
        this.loanStartDate = LocalDate.now();
        this.loanReturnDate = LocalDate.now().plusDays(30);
        this.effectiveReturnDate = effectiveReturnDate;
    }

}
