package com.netcracker.repositories;

import com.netcracker.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT DISTINCT b.name FROM Book as b")
    List<String> getDistinctName();
    @Query("SELECT DISTINCT b.cost FROM Book as b")
    List<Integer> getDistinctCost();

    @Query("SELECT b.name,b.cost FROM Book AS b WHERE b.name LIKE '%WINDOWS%' OR b.cost > 20000 ORDER BY b.name,b.cost")
    List<String> getBooksByNameAndCost();
}
