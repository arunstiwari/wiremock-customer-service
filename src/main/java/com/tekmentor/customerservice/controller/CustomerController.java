package com.tekmentor.customerservice.controller;

import com.tekmentor.customerservice.model.Customer;
import com.tekmentor.customerservice.model.Order;
import com.tekmentor.customerservice.model.ShippingStatus;
import com.tekmentor.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.fetchAll();
    }

    @GetMapping("/customers/{id}")
    public Customer specificCustomer(@PathVariable("id") String id){
        return customerService.fetchCustomerWithId(id);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.addNewCustomer(customer);
        System.out.println("newCustomer = " + newCustomer);
        return newCustomer.getId();
    }

    @GetMapping("/customers/{id}/orders")
    public Order fetchOrderForGivenCustomer(@PathVariable("id") String id){
        return customerService.fetchOrders(id);
    }

    @GetMapping("/customers/shipping/{orderId}")
    public ShippingStatus fetchShippingStatusForGivenOrderId(@PathVariable("orderId") String orderId){
        return customerService.fetchStatusForGivenOrderId(orderId);
    }

}
