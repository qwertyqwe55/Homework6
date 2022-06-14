package com.netcracker.controllers;


import com.netcracker.exceptions.ResourceNotFoundException;
import com.netcracker.models.Book;
import com.netcracker.models.Shop;
import com.netcracker.repositories.BookRepository;
import com.netcracker.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/shops")
    public List<Shop> getAllShops(){
        return shopRepository.findAll();
    }

    @GetMapping("/shops/id")
    public ResponseEntity<Shop> getShopById(@RequestParam("id") Long id
    ) throws ResourceNotFoundException {
        Shop shop =  shopRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + id));
        return ResponseEntity.ok().body(shop);
    }

    @PostMapping("/shops")
    public Shop createShop( @ModelAttribute Shop shop) {
        return shopRepository.save(shop);
    }


    @PutMapping("/shops/id")
    public ResponseEntity<Shop> updateShop(@RequestParam("id") Long ID,
                                           @ModelAttribute Shop shopDetails) throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + ID));

        shop.setDiscount(shopDetails.getDiscount());
        shop.setLocation(shopDetails.getLocation());
        shop.setName(shopDetails.getName());


        final Shop updateShop = shopRepository.save(shop);
        return ResponseEntity.ok(updateShop);
    }

    @PutMapping("/shops/id/location")
    public ResponseEntity<Shop> updateLocationShop(@RequestParam("id") Long id,
                                               @RequestParam("location") String location) throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + id));
        shop.setLocation(location);
        final Shop updateShop = shopRepository.save(shop);
        return ResponseEntity.ok(updateShop);
    }

    @PutMapping("/shops/id/name")
    public ResponseEntity<Shop> updateNameShop(@RequestParam("id") Long ID,
                                                  @RequestParam("name") String name) throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + ID));
        shop.setName(name);
        final Shop updateShop = shopRepository.save(shop);
        return ResponseEntity.ok(updateShop);
    }



    @DeleteMapping("/shops/id")
    public Map<String, Boolean> deleteEmployee(@RequestParam("id") Long ID)
            throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + ID));

        shopRepository.delete(shop);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/shops/getTaskB")
    public List<String> getTaskB(){
        return shopRepository.getNames();
    }
}