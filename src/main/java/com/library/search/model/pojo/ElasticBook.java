package com.library.search.model.pojo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "books", createIndex = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElasticBook {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Keyword, name = "title"),
            otherFields = @InnerField(suffix = "search", type = FieldType.Search_As_You_Type))
    private String title;

    @MultiField(mainField = @Field(type = FieldType.Keyword, name = "author"),
            otherFields = @InnerField(suffix = "search", type = FieldType.Search_As_You_Type))
    private String author;

    @Field(type = FieldType.Keyword, name = "isbn")
    private String isbn;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Integer, name = "year")
    private Integer year;

    @Field(type = FieldType.Keyword, name = "imagesrc")
    private String imagesrc;

    @Field(type = FieldType.Integer, name = "rentpriceday")
    private Integer rentpriceday;

    @Field(type = FieldType.Boolean, name = "available")
    private Boolean available;
}
