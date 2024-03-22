package org.example.entities;

import jakarta.persistence.Entity;

import java.util.Random;
@Entity
public class Magazine extends BibliographicalElement{

    //  ATTRIBUTES LIST:
    private Frequency frequency;

    //CONSTRUCTOR:
    public Magazine(){}
    public Magazine(String title, long pagesNumber) {
        super(title, pagesNumber);
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
                ", ISBN: " + id + "."
                ;
    }
}
