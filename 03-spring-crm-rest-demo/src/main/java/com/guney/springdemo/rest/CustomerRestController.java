package com.guney.springdemo.rest;

import com.guney.springdemo.entity.Customer;
import com.guney.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // autowire the CustomerService
    @Autowired
    private CustomerService customerService;

    // add mapping for GET /customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {

        return customerService.getCustomers();
    }

    // add mapping for GET /customers
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

        Customer thecustomer = customerService.getCustomer(customerId);

        if (thecustomer == null) {
            throw new CustomerNotFoundException("Customer NOT found - " + customerId);
        }

        return thecustomer;
    }

    // add mapping for POST /customers - add new customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {

        theCustomer.setId(0);

        customerService.saveCustomer(theCustomer);

        return theCustomer;

    }

    // add mapping for PUT /customers
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {

        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {

        Customer tempCustomer = customerService.getCustomer(customerId);

        if (tempCustomer == null) {
            throw new CustomerNotFoundException("Customer NOT found - " + customerId);
        }
        customerService.deleteCustomer(customerId);

        return "Deleted customer id - " + customerId;
    }
}
