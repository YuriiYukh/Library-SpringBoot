package com.github.yuriiyukh.libraryapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.yuriiyukh.libraryapplication.models.Book;
import com.github.yuriiyukh.libraryapplication.models.Person;
import com.github.yuriiyukh.libraryapplication.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    
    private final PeopleRepository peopleRepository;
    
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    
    public Person findById(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }
    
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
    
    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getAssignedBooks(int id) {
        List<Book> books = new ArrayList<>();
        Person person = peopleRepository.findById(id).orElse(null);
        if (person != null){
            books = person.getBooks();
        }
        return books;
    }

}
