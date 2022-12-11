package com.github.yuriiyukh.libraryapplication.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.yuriiyukh.libraryapplication.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

}
