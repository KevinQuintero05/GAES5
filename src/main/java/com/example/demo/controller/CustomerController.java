package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.repository.ICustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CustomerController {

    private final ICustomerRepository iCustomerRepository;

    public CustomerController( ICustomerRepository iCustomerRepository){
        this.iCustomerRepository = iCustomerRepository;
    }

    @GetMapping ("/customers")
    public List<Customer> GetCustomers(){

        return iCustomerRepository.findAll();
    }

    @PostMapping ("/customers")
    public Customer NewCustomer(@RequestBody Customer newCustomer){
        return iCustomerRepository.save(newCustomer);
    }

    @GetMapping("/customers/{id}")
    public Customer GetCustomer(@PathVariable long id){
        return iCustomerRepository.findById(id).get();
    }

    @PutMapping("/customers/{id}")
    public Customer UpdateCustomer(@RequestBody Customer customer, @PathVariable long id){
        Customer customerdb = iCustomerRepository.findById(id).get();
        customerdb.setDocument(customer.getDocument());
        customerdb.setEmail(customer.getEmail());
        customerdb.setName(customer.getName());
        return iCustomerRepository.save(customerdb);
    }

    @DeleteMapping("/customers/{id}")
    public void DeleteCustomer(@PathVariable long id){
        iCustomerRepository.deleteById(id);
    }
}
