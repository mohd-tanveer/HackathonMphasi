package com.hackerearth.hackathonHackerearth;


import com.hackerearth.hackathonHackerearth.Dto.BookDTO;
import com.hackerearth.hackathonHackerearth.Entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ControllerClass {

    @Autowired
    private SaveDataRepos saveDataRepos;

    @GetMapping("/")
    List<BookDTO> getDetails() {
        var response = saveDataRepos.findAll();
        List<BookDTO> getbooklist = new ArrayList<>();
        for (Book book : response) {
            BookDTO b = BookDTO.builder()
                    .bookID(book.getBookID()).title(book.getTitle())
                    .authors(book.getAuthors())
                    .average_rating(book.getAverage_rating())
                    .isbn(book.getIsbn()).language_code(book.getLanguage_code())
                    .ratings_count(book.getRatings_count()).price(book.getPrice()).build();
            getbooklist.add(b);
        }
        //System.out.println(getbooklist);
        return getbooklist;

    }

    @GetMapping("/{id}")
    BookDTO getDetailsById(@PathVariable("id") Long id) {
        var book = saveDataRepos.findById(id);
        BookDTO b = BookDTO.builder()
                .bookID(book.get().getBookID()).title(book.get().getTitle())
                .authors(book.get().getAuthors())
                .average_rating(book.get().getAverage_rating())
                .isbn(book.get().getIsbn()).language_code(book.get().getLanguage_code())
                .ratings_count(book.get().getRatings_count()).price(book.get().getPrice()).build();

        return b;

    }

    @GetMapping("/search/{term}")
    List<BookDTO> getDetailsByFuzzySearch(@PathVariable String term) {
        var response = saveDataRepos.findAllBookByTitle(term.toLowerCase());
        List<BookDTO> getbooklist = new ArrayList<>();
        for (Book book : response) {
            BookDTO b = BookDTO.builder()
                    .bookID(book.getBookID()).title(book.getTitle())
                    .authors(book.getAuthors())
                    .average_rating(book.getAverage_rating())
                    .isbn(book.getIsbn()).language_code(book.getLanguage_code())
                    .ratings_count(book.getRatings_count()).price(book.getPrice()).build();
            getbooklist.add(b);
        }
        //System.out.println(getbooklist);
        return getbooklist;

    }
}
