package com.hackerearth.hackathonHackerearth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaveDataRepos extends JpaRepository<Book, Long> {

    @Query("select b from Book b where lower(b.title) like %:term% ")
    List<Book> findAllBookByTitle(@Param("term") String term);
}
