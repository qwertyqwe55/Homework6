package com.netcracker.repositories;

import com.netcracker.models.Book;
import com.netcracker.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT DISTINCT c.location FROM Customer as c")
    List<String> getDistinctLocation();

    @Query("SELECT c.surname, c.discount FROM Customer as c where c.location = 'Нижегородская область'")
    List<String> getNizniiNovgorod();

}
