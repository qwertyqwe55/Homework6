package com.netcracker.repositories;

import com.netcracker.models.Book;
import com.netcracker.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Query("SELECT s.name FROM Shop AS s WHERE s.location= :#{#firstLocation} OR s.location = :#{#secondLocation}")
    List<String> getNames(@Param("firstLocation") String firstLocation, @Param("secondLocation") String secondLocation);
}
