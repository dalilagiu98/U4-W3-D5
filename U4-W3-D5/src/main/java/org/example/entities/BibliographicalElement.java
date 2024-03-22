package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Random;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "findByYear", query = "SELECT be FROM BibliographicalElement be WHERE be.publicationYear = :publicationYear")
@NamedQuery(name = "findByAuthor", query = "SELECT b FROM Book b WHERE LOWER(b.author) = LOWER(:author)")
@NamedQuery(name = "findByTitle", query = "SELECT be FROM BibliographicalElement be WHERE LOWER (be.title) LIKE LOWER (:title)")
public abstract class BibliographicalElement {

    //ATTRIBUTES LIST
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String title;
    protected int publicationYear;
    protected long pagesNumber;

    //CONSTRUCTOR:
    public BibliographicalElement(){}
    public BibliographicalElement (String title, long pagesNumber) {
        Random random = new Random();
        this.title = title;
        this.publicationYear = random.nextInt(1455, LocalDate.now().getYear()); //print a random number from 1455 (year of invention of printing) to actual year
        if(pagesNumber < 10){
            throw new IllegalArgumentException("YOU MUST ENTER A MINIMUM OF 10 PAGES!");
        }
        this.pagesNumber = pagesNumber;
    }

    //   METHODS:
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
}