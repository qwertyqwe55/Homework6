package com.netcracker.controllers;


import com.netcracker.exceptions.ResourceNotFoundException;
import com.netcracker.models.Book;
import com.netcracker.models.Shop;
import com.netcracker.repositories.BookRepository;
import com.netcracker.repositories.ShopRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable(value = "id") Long id
    ) throws ResourceNotFoundException {
        Shop shop =  shopRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + id));
        return ResponseEntity.ok().body(shop);
    }

    @PostMapping("/shops")
    public Shop createShop( @RequestBody Shop shop) {
        return shopRepository.save(shop);
    }


    @PutMapping("/shops/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable(value = "id") Long ID,
                                           @RequestBody Shop shopDetails) throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + ID));

        shop.setDiscount(shopDetails.getDiscount());
        shop.setLocation(shopDetails.getLocation());
        shop.setName(shopDetails.getName());


        final Shop updateShop = shopRepository.save(shop);
        return ResponseEntity.ok(updateShop);
    }

    @PutMapping("/shops/{id}/{location}")
    public ResponseEntity<Shop> updateLocationShop(@PathVariable(value = "id") Long id,
                                                   @PathVariable(value = "location")String location) throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + id));
        shop.setLocation(location);
        final Shop updateShop = shopRepository.save(shop);
        return ResponseEntity.ok(updateShop);
    }

    @PutMapping("/shops/{id}/{name}")
    public ResponseEntity<Shop> updateNameShop(@PathVariable(value = "id") Long ID,
                                               @PathVariable(value = "name") String name) throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + ID));
        shop.setName(name);
        final Shop updateShop = shopRepository.save(shop);
        return ResponseEntity.ok(updateShop);
    }



    @DeleteMapping("/shops/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long ID)
            throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found for this id :: " + ID));

        shopRepository.delete(shop);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value = "Названия магазинов firstLocation или secondLocation")
    @GetMapping("/shops/{firstLocation}/{secondLocation}")
    public List<String> getTaskB(@PathVariable(value = "firstLocation") String firstLocation, @PathVariable(value = "secondLocation") String secondLocation){
        return shopRepository.getNames(firstLocation,secondLocation);
    }
}
