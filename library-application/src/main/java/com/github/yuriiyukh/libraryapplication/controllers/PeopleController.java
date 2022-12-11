package com.github.yuriiyukh.libraryapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.yuriiyukh.libraryapplication.models.Person;
import com.github.yuriiyukh.libraryapplication.services.PeopleService;
import com.github.yuriiyukh.libraryapplication.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    
    private static final String REDIRECT_TO_PEOPLE = "redirect:/people";
    private static final String BOOKS_KEY = "books";
    private static final String PERSON_KEY = "person";
    private static final String PEOPLE_KEY = "people";
    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    
    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }
    
    @GetMapping()
    public String index(Model model) {
        model.addAttribute(PEOPLE_KEY, peopleService.findAll());
        return "people/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute(PERSON_KEY, peopleService.findById(id));
        model.addAttribute(BOOKS_KEY, peopleService.getAssignedBooks(id));
        return "people/show";
    }
    
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute(PERSON_KEY, new Person());
        return "people/new";
    }
    
    @PostMapping()
    public String create(@ModelAttribute(PERSON_KEY) @Validated Person person, BindingResult bindingResult) {
        
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        
        peopleService.save(person);
        return REDIRECT_TO_PEOPLE;
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute(PERSON_KEY, peopleService.findById(id));
        
        return "people/edit";
        
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute(PERSON_KEY) @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        
        peopleService.update(id, person);
        return REDIRECT_TO_PEOPLE;
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        
        return REDIRECT_TO_PEOPLE;
    }

}
