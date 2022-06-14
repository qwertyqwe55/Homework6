package com.netcracker.controllers;

import com.netcracker.exceptions.ResourceNotFoundException;
import com.netcracker.models.Book;
import com.netcracker.models.Customer;
import com.netcracker.repositories.BookRepository;
import com.netcracker.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/id")
    public ResponseEntity<Customer> getCustomerById(@RequestParam("id") Long customerId
    ) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerId));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer( @ModelAttribute Customer customer) {
        return customerRepository.save(customer);
    }


    @PutMapping("/customers/id")
    public ResponseEntity<Customer> updateCustomer(@RequestParam("id") Long customerID,
                                           @ModelAttribute Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerID));

        customer.setDiscount(customerDetails.getDiscount());
        customer.setLocation(customerDetails.getLocation());
        customer.setSurname(customerDetails.getSurname());

        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }

    @PutMapping("/customers/id/surname")
    public ResponseEntity<Customer> updateSurnameCustomer(@RequestParam(value = "id") Long customerID,
                                               @RequestParam("surname") String surname) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerID));
        customer.setSurname(surname);
        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }

    @PutMapping("/customers/id/location")
    public ResponseEntity<Customer> updateLocationCustomer(@RequestParam(value = "id") Long customerID,
                                                  @RequestParam(value = "location") String location) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerID));
        customer.setLocation(location);
        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }



    @DeleteMapping("/customers/id")
    public Map<String, Boolean> deleteCustomer(@RequestParam("id") Long customerID)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerID));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/customers/distinctLocation")
    public List<String> getDistinctLocation(){
        return customerRepository.getDistinctLocation();
    }

    @GetMapping("/customers/getNovgorod")
    public List<String> getNizniiNovgorod(){
        return customerRepository.getNizniiNovgorod();
    }


}