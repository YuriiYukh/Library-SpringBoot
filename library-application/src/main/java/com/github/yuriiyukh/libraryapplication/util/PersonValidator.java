package com.github.yuriiyukh.libraryapplication.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.github.yuriiyukh.libraryapplication.models.Person;
import com.github.yuriiyukh.libraryapplication.repositories.PeopleRepository;

@Component
public class PersonValidator implements Validator{

    private final PeopleRepository peopleRepository;
    
    @Autowired
    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        
        if (peopleRepository.findByName(person.getName()) != null) {
            errors.rejectValue("name", "", "Person already exsists");
        }
    }

}
