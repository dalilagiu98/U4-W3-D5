package org.example.entities;

import jakarta.persistence.Entity;

@Entity
public class Book extends BibliographicalElement{

    //ATTRIBUTES LIST:
    private String author;
    private String genre;

    //CONSTRUCTOR:
    public Book(){}
    public Book(String isbn, String title, long pagesNumber, String author, String genre) {
        super(isbn, title, pagesNumber);
        this.author = author;
        this.genre = genre;
    }

    //METHODS:
    @Override
    public String toString() {
        return "BOOK:" +
                "'" + title  + "'" +
                " by " + author +", genre: " + genre + ", ISBN :" + isbn +
                ", publication Year:" + publicationYear +
                ", pages:" + pagesNumber +
                ".";
    }

    public String getAuthor() {
        return author;
    }
}