package com.hackerearth.hackathonHackerearth.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOK")
public class Book {
    @Id
    private Long bookID;
    @Column(length = 65535)
    private String title;
    @Column(length = 1024)
    private String authors;
    @Column(length = 1024)
    private String average_rating;
    @Column(length = 1024)
    private String isbn;
    @Column(length = 1024)
    private String language_code;
    @Column(length = 1024)
    private String ratings_count;
    @Column(length = 1024)
    private String price;
}
