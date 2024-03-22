package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Random;
@Entity
public class Magazine extends BibliographicalElement{

    //  ATTRIBUTES LIST:
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    //CONSTRUCTOR:
    public Magazine(){}
    public Magazine(String isbn, String title, long pagesNumber) {
        super(isbn, title, pagesNumber);
        Random random = new Random();
        this.frequency = Frequency.values()[random.nextInt(0,3)]; //get a random value with index from 0 to 2
    }

    //METHODS:

    @Override
    public String toString() {
        return "MAGAZINE:" +
                "'" + title + "'" +
                ", frequency: " + frequency +
                ", publication year: " + publicationYear +
                ", pagesNumber: " + pagesNumber +
                ", ISBN: " + isbn + "."
                ;
    }
}
