package com.netcracker.controllers;

import com.netcracker.exceptions.ResourceNotFoundException;
import com.netcracker.models.Book;
import com.netcracker.models.Customer;
import com.netcracker.repositories.BookRepository;
import com.netcracker.repositories.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId
    ) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerId));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer( @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }


    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerID,
                                           @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerID));

        customer.setDiscount(customerDetails.getDiscount());
        customer.setLocation(customerDetails.getLocation());
        customer.setSurname(customerDetails.getSurname());

        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }

    @PutMapping("/customers/{id}/{surname}")
    public ResponseEntity<Customer> updateSurnameCustomer(@PathVariable(value = "id") Long customerID,
                                               @PathVariable(value = "surname") String surname) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerID));
        customer.setSurname(surname);
        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }

    @PutMapping("/customers/{id}/{location}")
    public ResponseEntity<Customer> updateLocationCustomer(@PathVariable(value = "id") Long customerID,
                                                  @PathVariable(value = "location") String location) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerID));
        customer.setLocation(location);
        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }



    @DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerID)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerID));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value = "Все различные районы проживания покупателей")
    @GetMapping("/customers/distinctLocation")
    public List<String> getDistinctLocation(){
        return customerRepository.getDistinctLocation();
    }


    @ApiOperation(value = "фамилии и размер скидки всех покупателей, проживающих в location")
    @GetMapping("/customers/{location}")
    public List<String> getNizniiNovgorod(@PathVariable(value = "location") String location){
        return customerRepository.getNizniiNovgorod(location);
    }


}