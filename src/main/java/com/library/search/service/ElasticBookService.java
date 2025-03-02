package com.library.search.service;

import com.library.search.model.pojo.ElasticBook;
import com.library.search.model.request.CreateBookRequest;

import java.util.List;

public interface ElasticBookService {

    ElasticBook getBookByIsbn(String bookIsbn);

    List<ElasticBook> getAvailableBooks();

    ElasticBook getBookByTitle(String bookTitle);

    List<ElasticBook> searchByTitle(String bookTitle);

    List<ElasticBook> searchByDescription(String bookDescription);

    ElasticBook createBook(CreateBookRequest request);
}
