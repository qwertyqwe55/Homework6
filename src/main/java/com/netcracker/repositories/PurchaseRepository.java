package com.netcracker.repositories;

import com.netcracker.models.Book;
import com.netcracker.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {


    @Query("SELECT DISTINCT p.datePurchases FROM Purchase AS p")
    List<Date> getDistinctDate();

    @Query("SELECT p.datePurchases, c.surname, c.discount, b.name, p.count FROM Purchase as p JOIN Customer c ON c.id = p.idCustomer " +
            "JOIN Book b ON b.idBook = p.idBook")
    List<String> getDateAndSurname();

    @Query("SELECT c.surname, s.name FROM Purchase as p JOIN Customer c ON c.id = p.idCustomer " +
            "JOIN Shop s ON s.id = p.idShop")
    List<String> getSurnameAndName();

    @Query("SELECT p.id, c.surname FROM Purchase AS p JOIN Customer c ON c.id = p.idCustomer WHERE p.cost > 60000")
    List<String> getBigCost();

    @Query("SELECT s.location, c.surname, p.datePurchases FROM Purchase AS p JOIN Customer c ON c.id = p.idCustomer " +
            "JOIN Shop s ON s.id=p.idShop WHERE c.location=s.location OR MONTH(p.datePurchases) <= 3 ORDER BY c.surname")
    List<String> getSimilarLocation();

    @Query("SELECT s.name FROM Purchase p JOIN Shop s ON p.idShop = s.id " +
            "join Customer c on c.id = p.idCustomer WHERE s.location <> 'Автозаводский район' AND c.discount >= 10 AND c.discount <= 15")
    List<String> getDiscount();

    @Query("SELECT b.name,b.storage,b.count,p.cost FROM Purchase p JOIN Book b ON p.idBook = b.idBook " +
            "JOIN Shop s ON s.id = p.idShop WHERE s.location = b.storage AND b.count > 10 ORDER BY p.cost")
    List<String> getBooks();


}
