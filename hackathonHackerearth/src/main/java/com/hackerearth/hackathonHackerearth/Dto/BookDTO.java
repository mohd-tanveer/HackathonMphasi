package com.hackerearth.hackathonHackerearth.Dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"bookID", "title", "authors", "average_rating", "isbn", "language_code", "ratings_count", "price"})
public class BookDTO {
    private Long bookID;
    private String title;
    private String authors;
    private String average_rating;
    private String isbn;
    private String language_code;
    private String ratings_count;
    private String price;

}
