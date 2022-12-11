package com.github.yuriiyukh.libraryapplication.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 75, message = "Name should be between 2 and 75 chars")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Enter full name in format '(Last Name) (First Name) (Patronymic)'")
    @Column(name = "full_name")
    private String name;

    @Min(value = 1900, message = "Age should be above 1900")
    @Max(value = 2022, message = "Age should be lower then 2022")
    @Column(name = "birth_year")
    private int birthYear;

    @Cascade(CascadeType.SAVE_UPDATE)
    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {

    }

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;

    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Optional<Book> book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }

        if (book.isPresent()) {
            this.books.add(book.get());
            book.get().setOwner(this);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthYear, books, id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        return birthYear == other.birthYear && Objects.equals(books, other.books) && id == other.id
                && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", birthYear=" + birthYear + ", books=" + books + "]";
    }
}
