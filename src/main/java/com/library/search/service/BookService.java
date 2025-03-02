package com.library.search.service;

import com.library.search.model.pojo.Book;
import com.library.search.model.request.CreateBookRequest;

import java.util.List;

public interface BookService {

    List<Book> getBooks();

    Book getBook(String bookId);

    Boolean removeBook(String bookId);

    Book modifyBook(String bookId, CreateBookRequest request);

    Book createBook(CreateBookRequest request);
}
