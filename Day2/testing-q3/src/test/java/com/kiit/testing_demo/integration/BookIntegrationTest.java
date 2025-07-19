package com.kiit.testing_demo.integration;

import com.kiit.testing_demo.model.Book;
import com.kiit.testing_demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookIntegrationTest {

    @Autowired
    private BookRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll(); // Ensure test isolation
    }

    @Test
    void shouldSaveBook() {
        Book book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");

        Book saved = repository.save(book);

        assertThat(saved.getId()).isNotNull();
        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    void shouldRetrieveBookById() {
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        Book saved = repository.save(book);

        Optional<Book> found = repository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void shouldUpdateBook() {
        Book book = new Book();
        book.setTitle("Intro to Java");
        book.setAuthor("Anonymous");
        Book saved = repository.save(book);

        saved.setAuthor("Updated Author");
        Book updated = repository.save(saved);

        assertThat(updated.getAuthor()).isEqualTo("Updated Author");
    }

    @Test
    void shouldDeleteBook() {
        Book book = new Book();
        book.setTitle("Delete Me");
        book.setAuthor("To Be Removed");
        Book saved = repository.save(book);

        repository.deleteById(saved.getId());

        Optional<Book> deleted = repository.findById(saved.getId());
        assertThat(deleted).isEmpty();
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void shouldSaveMultipleBooks() {
        Book book1 = new Book();
        book1.setTitle("Book A");
        book1.setAuthor("Author A");

        Book book2 = new Book();
        book2.setTitle("Book B");
        book2.setAuthor("Author B");

        repository.save(book1);
        repository.save(book2);

        assertThat(repository.findAll()).hasSize(2);
    }
}
