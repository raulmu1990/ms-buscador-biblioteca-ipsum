package com.library.search.controller;

import com.library.search.model.pojo.Book;
import com.library.search.model.request.CreateBookRequest;
import com.library.search.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final BookService service;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {

        List<Book> books = service.getBooks();

        if (books != null) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable String bookId) {

        Book book = service.getBook(bookId);

        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Boolean> removeBook(@PathVariable String bookId) {

        Boolean removed = service.removeBook(bookId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/books/{bookId}")
    public ResponseEntity<Book> patchBook(@PathVariable String bookId, @RequestBody CreateBookRequest request) {

        Book patchedBook = service.modifyBook(bookId, request);
        return ResponseEntity.ok(patchedBook);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> getBook(@RequestBody CreateBookRequest request) {

        Book createdBook = service.createBook(request);

        if (createdBook != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Manejo de la excepcion para cuando un libro no se encuentra
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
