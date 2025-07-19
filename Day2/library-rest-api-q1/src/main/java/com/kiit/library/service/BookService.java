package com.kiit.library.service;

import com.kiit.library.dto.BookDto;
import com.kiit.library.exception.ResourceNotFoundException;
import com.kiit.library.mapper.BookMapper;
import com.kiit.library.model.Book;
import com.kiit.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookDto addBook(BookDto dto) {
        Book saved = repository.save(BookMapper.toEntity(dto));
        return BookMapper.toDto(saved);
    }

    public BookDto getBookById(Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID " + id));
        return BookMapper.toDto(book);
    }

    public List<BookDto> getAllBooks() {
        return repository.findAll().stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto updateBook(Long id, BookDto dto) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID " + id));

        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setYear(dto.getYear());

        Book updated = repository.save(existing);
        return BookMapper.toDto(updated);
    }

    public void deleteBook(Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID " + id));
        repository.delete(book);
    }
}
