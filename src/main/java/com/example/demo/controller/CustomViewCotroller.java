package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomViewCotroller {

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @GetMapping("/customers")
    public String GetCustomers(Model model) {
        List<Customer> listCustomer = iCustomerRepository.findAll();
        model.addAttribute("customers", listCustomer);
        return "Customers/Customers";
    }

    @GetMapping("/customers/new")
    public String GetShowCreateCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customers/Create";
    }

    @PostMapping("/customers/save")
    public String SaveCustomer(Customer customer) {
        iCustomerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String ShowUpdateCustomer(Model model, @PathVariable long id) {
        Customer customer = iCustomerRepository.findById(id).get();
        model.addAttribute("customer", customer);
        return "Customers/Edit";
    }

    @PostMapping("/customers/edit/{id}")
    public String UpdateCustomer(@PathVariable long id, Customer customer){
        iCustomerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/delete/{id}")
    public String DeleteCustomer(@PathVariable long id){
        iCustomerRepository.deleteById(id);
        return "redirect:/customers";
    }
}
