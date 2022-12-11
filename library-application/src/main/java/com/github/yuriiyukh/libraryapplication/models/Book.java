package com.github.yuriiyukh.libraryapplication.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Min(value = -1, message = "Year should be above 0")
    @Max(value = 2030, message = "Year should be less than 2030")
    @Column(name = "year")
    private int year;
    
    @Size(min = 1, max = 200, message = "Author name length should be between 200 characters")
    @Column(name = "author")
    private String author;
    
    @Size(min = 1, max = 200, message = "Book name length should be between 200 characters")
    @Column(name = "name")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
    
    public Book(int id, int year, String author, String name) {
        this.id = id;
        this.year = year;
        this.author = author;
        this.name = name;
    }

    public Book() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, id, name, owner, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        return Objects.equals(author, other.author) && id == other.id && Objects.equals(name, other.name)
                && Objects.equals(owner, other.owner) && year == other.year;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", year=" + year + ", author=" + author + ", name=" + name + ", owner=" + owner + "]";
    }
    
    
    
    
}
