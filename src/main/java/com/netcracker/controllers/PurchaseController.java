package com.netcracker.controllers;

import com.netcracker.exceptions.ResourceNotFoundException;
import com.netcracker.models.Book;
import com.netcracker.models.Purchase;
import com.netcracker.repositories.BookRepository;
import com.netcracker.repositories.PurchaseRepository;
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

    @GetMapping("/purchases/getSurnameAndName")
    public List<String> getSurnameAndName(){
        return purchaseRepository.getSurnameAndName();
    }

    @GetMapping("/purchases/getDistinctDate")
    public List<Date> getDistinctDate(){
        return purchaseRepository.getDistinctDate();
    }

    @GetMapping("/purchases/getSimilarLocation")
    public List<String> getSimilarLocation(){
        return purchaseRepository.getSimilarLocation();
    }
    @GetMapping("/purchases/getBigCost")
    public List<String> getBigCost(){
        return purchaseRepository.getBigCost();
    }

    @GetMapping("/purchases/getDiscount")
    public List<String> getDiscount(){
        return purchaseRepository.getDiscount();
    }

    @GetMapping("/purchases/getBooks")
    public List<String> getBooks(){
        return purchaseRepository.getBooks();
    }

    @GetMapping("/purchases/getDateAndSurname")
    public List<String> getDateAndSurname(){
        return purchaseRepository.getDateAndSurname();
    }
}
