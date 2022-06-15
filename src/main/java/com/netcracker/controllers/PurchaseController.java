package com.netcracker.controllers;

import com.netcracker.exceptions.ResourceNotFoundException;
import com.netcracker.models.Book;
import com.netcracker.models.Purchase;
import com.netcracker.repositories.BookRepository;
import com.netcracker.repositories.PurchaseRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases(){
        return purchaseRepository.findAll();
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable(value = "id") Long id
    ) throws ResourceNotFoundException {
        Purchase purchase = purchaseRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Purchase not found for this id :: " + id));
        return ResponseEntity.ok().body(purchase);
    }

    @PostMapping("/purchases")
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return purchaseRepository.save(purchase);
    }


    @PutMapping("/purchases/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable(value = "id") Long ID,
                                           @RequestBody Purchase purchaseDetails) throws ResourceNotFoundException {
        Purchase purchase = purchaseRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found for this id :: " + ID));

        purchase.setCost(purchaseDetails.getCost());
        purchase.setCount(purchaseDetails.getCount());
        purchase.setDatePurchases(purchaseDetails.getDatePurchases());
        purchase.setIdBook(purchaseDetails.getIdBook());
        purchase.setIdCustomer(purchaseDetails.getIdCustomer());
        purchase.setIdShop(purchaseDetails.getIdShop());

        final Purchase updatePurchase = purchaseRepository.save(purchase);
        return ResponseEntity.ok(updatePurchase);
    }

    @PatchMapping("/purchase/{id}/{cost}")
    public ResponseEntity<Purchase> updateCostPurchase(@PathVariable(value = "id") Long ID,
                                               @PathVariable(value = "cost") Integer cost) throws ResourceNotFoundException {
        Purchase purchase = purchaseRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found for this id :: " + ID));
        purchase.setCost(cost);
        final Purchase updatePurchase = purchaseRepository.save(purchase);
        return ResponseEntity.ok(updatePurchase);
    }

    @PatchMapping("/purchases/{id}/{count}")
    public ResponseEntity<Purchase> updateCountPurchase(@PathVariable(value = "id") Long ID,
                                                        @PathVariable(value = "count") Integer count) throws ResourceNotFoundException {
        Purchase purchase = purchaseRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found for this id :: " + ID));
        purchase.setCount(count);
        final Purchase updatePurchase = purchaseRepository.save(purchase);
        return ResponseEntity.ok(updatePurchase);
    }



    @DeleteMapping("/purchases/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long ID)
            throws ResourceNotFoundException {
        Purchase purchase = purchaseRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found for this id :: " + ID));

        purchaseRepository.delete(purchase);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @ApiOperation(value = "Фамилия покупателя и название магазина, где производилась покупка")
    @GetMapping("/purchases/getSurnameAndName")
    public List<String> getSurnameAndName(){
        return purchaseRepository.getSurnameAndName();
    }

    @ApiOperation(value = "Все различные месяцы, когда производились покупки")
    @GetMapping("/purchases/getDistinctDate")
    public List<Date> getDistinctDate(){
        return purchaseRepository.getDistinctDate();
    }

    @ApiOperation(value = "покупки, сделанные покупателем в своем районе не ранее марта месяца.")
    @GetMapping("/purchases/getSimilarLocation")
    public List<String> getSimilarLocation(){
        return purchaseRepository.getSimilarLocation();
    }

    @ApiOperation(value = "номер заказа, фамилию покупателя и дату для покупок, в которых было продано книг на сумму не меньшую чем cost руб")
    @GetMapping("/purchases/{cost}")
    public List<String> getBigCost(@PathVariable(value = "cost") Integer cost){
        return purchaseRepository.getBigCost(cost);
    }

    @ApiOperation(value = "Магазины, расположенные в любом районе, кроме location, где покупали книги те, у кого скидка от minDiscount до maxDiscount%")
    @GetMapping("/purchases/{location}/{minDiscount}/{maxDiscount}")
    public List<String> getDiscount(@PathVariable(value = "location") String location, @PathVariable(value = "minDiscount") Integer minDiscount,
    @PathVariable(value = "maxDiscount") Integer maxDiscount){
        return purchaseRepository.getDiscount(location, minDiscount, maxDiscount);
    }

    @ApiOperation(value = "данные по покупке книг (название, район складирования, количество), приобретенных в районе складирования и\n" +
            "    содержащихся в запасе более count штук")
    @GetMapping("/purchases/{count}")
    public List<String> getBooks(@PathVariable(value = "count") Integer count){
        return purchaseRepository.getBooks(count);
    }

    @ApiOperation(value = "Дата, фамилия покупателя, скидка, название и количество купленных книг")
    @GetMapping("/purchases/getDateAndSurname")
    public List<String> getDateAndSurname(){
        return purchaseRepository.getDateAndSurname();
    }
}
