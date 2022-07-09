package com.application.SQL.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository 
                extends JpaRepository<Book, Long> {

    List<Book> findByAutorStartsWithIgnoreCase(String autor);
}