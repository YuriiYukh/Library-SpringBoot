package com.github.yuriiyukh.libraryapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.yuriiyukh.libraryapplication.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);
}
