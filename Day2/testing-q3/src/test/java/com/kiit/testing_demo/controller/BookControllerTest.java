package com.kiit.testing_demo.controller;

import com.kiit.testing_demo.model.Book;
import com.kiit.testing_demo.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository repository;

    @Test
    void testGetBooks() throws Exception {
        Book book = new Book();
        book.setTitle("Spring Boot");
        book.setAuthor("Anurag");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Spring Boot"))
                .andExpect(jsonPath("$[0].author").value("Anurag"));
    }

    @Test
    void testGetBooks_whenEmptyList() throws Exception {
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetBooks_withMultipleBooks() throws Exception {
        Book book1 = new Book();
        book1.setTitle("Spring Boot");
        book1.setAuthor("Anurag");

        Book book2 = new Book();
        book2.setTitle("Java 17");
        book2.setAuthor("Krishna");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Spring Boot"))
                .andExpect(jsonPath("$[1].author").value("Krishna"));
    }


}
