package com.library.search.controller;

import com.library.search.model.pojo.ElasticBook;
import com.library.search.model.request.CreateBookRequest;
import com.library.search.service.ElasticBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ElasticSearchController {

    private final ElasticBookService service;

    // Con este endpoint obtenemos el libro por el ISBN proporcionado
    @GetMapping("/elastic/books/{bookIsbn}")
    public ResponseEntity<ElasticBook> getBookByIsbn(@PathVariable String bookIsbn) {

        ElasticBook book = service.getBookByIsbn(bookIsbn);

        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtenemos todos los libros disponibles
    @GetMapping("/elastic/books")
    public ResponseEntity<List<ElasticBook>> getBooks() {

        List<ElasticBook> books = service.getAvailableBooks();

        if (books != null) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtenemos el libro por su titulo
    @GetMapping("/elastic/books/match/{bookTitle}")
    public ResponseEntity<ElasticBook> getBookByTitle(@PathVariable String bookTitle) {

        ElasticBook book = service.getBookByTitle(bookTitle);

        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Hacemos una busqueda por titulos de todos los libros
    @GetMapping("/elastic/books/search/as-you-type/{titlePart}")
    public ResponseEntity<List<ElasticBook>> searchByTitle(@PathVariable String titlePart) {

        List<ElasticBook> book = service.searchByTitle(titlePart);

        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Hacemos una busqueda full-text de los libros por su descripcion
    @GetMapping("/elastic/books/search/full-text/{value}")
    public ResponseEntity<List<ElasticBook>> searchByDescription(@PathVariable String value) {

        List<ElasticBook> book = service.searchByDescription(value);

        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Creamos un nuevo libro
    @PostMapping("/elastic/books")
    public ResponseEntity<ElasticBook> getBook(@RequestBody CreateBookRequest request) {

        ElasticBook createdBook = service.createBook(request);

        if (createdBook != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
