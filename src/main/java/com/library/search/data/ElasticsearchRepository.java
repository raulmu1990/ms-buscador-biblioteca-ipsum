package com.library.search.data;

import com.library.search.model.pojo.ElasticBook;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ElasticsearchRepository {

    private final String[] titleSearchFields =
            {"title.search", "title.search_2gram", "title.search_3gram"};

    private final ElasticBookRepository bookRepo;

    private final ElasticsearchOperations elasticClient;

    public ElasticBook getByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn).orElse(null);
    }

    public ElasticBook getByTitle(String title) {
        return bookRepo.findByTitle(title).orElse(null);
    }

    public List<ElasticBook> getAvailable() {
        return bookRepo.findByAvailable(true);
    }

    public List<ElasticBook> searchByTitle(String titlePart) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        boolQuery.must(QueryBuilders.multiMatchQuery(titlePart, titleSearchFields)
                .type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));

        NativeSearchQueryBuilder nativeSearchQueryBuilder
                = new NativeSearchQueryBuilder().withQuery(boolQuery);

        Query query = nativeSearchQueryBuilder.build();

        SearchHits<ElasticBook> result = elasticClient.search(query, ElasticBook.class);

        return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public List<ElasticBook> searchByDescription(String description) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        boolQuery.must(QueryBuilders.matchQuery("description", description));

        NativeSearchQueryBuilder nativeSearchQueryBuilder
                = new NativeSearchQueryBuilder().withQuery(boolQuery);

        Query query = nativeSearchQueryBuilder.build();

        SearchHits<ElasticBook> result = elasticClient.search(query, ElasticBook.class);

        return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public ElasticBook saveBook(ElasticBook book) {
        return bookRepo.save(book);
    }
}
