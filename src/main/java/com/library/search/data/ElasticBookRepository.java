package com.library.search.data;

import com.library.search.model.pojo.ElasticBook;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface ElasticBookRepository extends ElasticsearchRepository<ElasticBook, String> {

    Optional<ElasticBook> findByIsbn(String isbn);

    Optional<ElasticBook> findByTitle(String title);

    List<ElasticBook> findByAvailable(Boolean available);

    ElasticBook save(ElasticBook book);
}
