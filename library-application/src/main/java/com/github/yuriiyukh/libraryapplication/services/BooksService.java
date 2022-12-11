package com.github.yuriiyukh.libraryapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.yuriiyukh.libraryapplication.models.Book;
import com.github.yuriiyukh.libraryapplication.models.Person;
import com.github.yuriiyukh.libraryapplication.repositories.BooksRepository;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        Optional<Book> person = booksRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public Optional<Person> getBookOwner(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            return Optional.of(book.get().getOwner());
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        selectedPerson.addBook(booksRepository.findById(id));
    }

    @Transactional
    public void unassign(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            book.get().getOwner().getBooks().remove(book.get());
            book.get().setOwner(null);
        }

    }
}
