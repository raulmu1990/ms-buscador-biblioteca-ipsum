package com.library.search.service;

import com.library.search.data.BookRepository;
import com.library.search.model.pojo.Book;
import com.library.search.model.request.CreateBookRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> getBooks() {
        List<Book> books = repository.findAll();
        return books.isEmpty() ? null : books;
    }

    @Override
    public Book getBook(String bookId) {

        return repository.findById(Long.valueOf(bookId)).orElse(null);
    }

    @Override
    public Boolean removeBook(String bookId) {
        Book book = repository.findById(Long.valueOf(bookId)).orElse(null);

        if (book != null) {
            repository.delete(book);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional
    public Book modifyBook(String bookId, CreateBookRequest request) {

        Book existingBook = repository.findById(Long.valueOf(bookId)).orElseThrow(() -> new RuntimeException("Book not found"));

        if (request.getTitle() != null) {
            existingBook.setTitle(request.getTitle());
        }

        if (request.getAuthor() != null) {
            existingBook.setAuthor(request.getAuthor());
        }

        if (request.getIsbn() != null) {
            existingBook.setIsbn(request.getIsbn());
        }

        if (request.getDescription() != null) {
            existingBook.setDescription(request.getDescription());
        }

        if (request.getYear() != null) {
            existingBook.setYear(request.getYear());
        }

        if (request.getRentpriceday() != null) {
            existingBook.setRentpriceday(request.getRentpriceday());
        }

        if (request.getAvailable() != null) {
            existingBook.setAvailable(request.getAvailable());
        }

        return repository.save(existingBook);
    }

    @Override
    public Book createBook(CreateBookRequest request) {

        if (
                request != null
                        && StringUtils.hasLength(request.getTitle().trim())
                        && StringUtils.hasLength(request.getAuthor().trim())
                        && StringUtils.hasLength(request.getIsbn().trim())
                        && StringUtils.hasLength(request.getDescription().trim())
                        && StringUtils.hasLength(request.getImagesrc().trim())
                        && request.getYear() != null
                        && request.getRentpriceday() != null
                        && request.getAvailable() != null
        )
        {
            Book book = Book.builder()
                    .title(request.getTitle())
                    .author(request.getAuthor())
                    .isbn(request.getIsbn())
                    .description(request.getDescription())
                    .imagesrc(request.getImagesrc())
                    .year(request.getYear())
                    .rentpriceday(request.getRentpriceday())
                    .available(request.getAvailable()).build();

            return repository.save(book);
        } else {
            return null;
        }
    }
}
