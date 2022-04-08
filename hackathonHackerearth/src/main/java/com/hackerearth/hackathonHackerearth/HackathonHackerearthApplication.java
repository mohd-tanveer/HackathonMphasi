package com.hackerearth.hackathonHackerearth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerearth.hackathonHackerearth.Entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor

public class HackathonHackerearthApplication {
    @Autowired
    SaveDataRepos saveDataRepos;

    public static void main(String[] args) {
        SpringApplication.run(HackathonHackerearthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            List<BookDTO> listOfBooks = restTemplate.getForObject(
                    "https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json", List.class, BookDTO.class);
            //System.out.println(listOfBooks);
            ObjectMapper mapper = new ObjectMapper();

            List<BookDTO> pojos = mapper.convertValue(
                    listOfBooks, new TypeReference<>(){});

            List<Book> bookListSave = new ArrayList<>();
            for (BookDTO book : pojos) {
                Book b = Book.builder()
                        .bookID(book.getBookID()).title(book.getTitle())
                        .authors(book.getAuthors())
                        .average_rating(book.getAverage_rating())
                        .isbn(book.getIsbn()).language_code(book.getLanguage_code())
                        .ratings_count(book.getRatings_count()).price(book.getPrice()).build();
                bookListSave.add(b);
            }
            var response = saveDataRepos.saveAll(bookListSave);
            //System.out.println(response);

        };
    }

}
