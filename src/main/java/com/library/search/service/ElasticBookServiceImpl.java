package com.library.search.service;

import com.library.search.data.ElasticsearchRepository;
import com.library.search.model.pojo.ElasticBook;
import com.library.search.model.request.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ElasticBookServiceImpl implements ElasticBookService {

    private final ElasticsearchRepository repo;

    @Override
    public ElasticBook getBookByIsbn(String bookIsbn) {
        return repo.getByIsbn(bookIsbn);
    }

    @Override
    public List<ElasticBook> getAvailableBooks() {
        return repo.getAvailable();
    }

    @Override
    public ElasticBook getBookByTitle(String bookTitle) {
        return repo.getByTitle(bookTitle);
    }

    @Override
    public List<ElasticBook> searchByTitle(String bookTitle) {
        return repo.searchByTitle(bookTitle);
    }

    @Override
    public List<ElasticBook> searchByDescription(String bookDescription) {
        return repo.searchByDescription(bookDescription);
    }

    @Override
    public ElasticBook createBook(CreateBookRequest request) {

        if (request != null
                && StringUtils.hasLength(request.getTitle().trim())
                && StringUtils.hasLength(request.getAuthor().trim())
                && StringUtils.hasLength(request.getIsbn().trim())
                && StringUtils.hasLength(request.getDescription().trim())
                && request.getYear() != null
                && StringUtils.hasLength(request.getImagesrc().trim())
                && request.getRentpriceday() != null
                && request.getAvailable() != null
        ) {
            ElasticBook book = ElasticBook.builder().id(String.valueOf(request.getTitle().hashCode()))
                    .title(request.getTitle()).author(request.getAuthor()).isbn(request.getIsbn())
                    .description(request.getDescription()).year(request.getYear())
                    .imagesrc(request.getImagesrc()).rentpriceday(request.getRentpriceday())
                    .available(request.getAvailable()).build();

            return repo.saveBook(book);
        } else {
            return null;
        }
    }
}
