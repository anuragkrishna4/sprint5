package com.kiit.testing_demo.repository;

import com.kiit.testing_demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
