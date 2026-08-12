package com.junior.library.repository;

import com.junior.library.entitie.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
