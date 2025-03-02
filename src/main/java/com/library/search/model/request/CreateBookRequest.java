package com.library.search.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private String title;
    private String author;
    private String isbn;
    private String description;
    private Integer year;
    private String imagesrc;
    private Integer rentpriceday;
    private Boolean available;
}
