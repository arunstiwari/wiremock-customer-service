package com.tekmentor.customerservice.service;

import com.tekmentor.customerservice.model.Customer;
import com.tekmentor.customerservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;
    private List<Customer> customers = new ArrayList<>();

    public  CustomerService() {

    }
    public List<Customer> fetchAll() {
        return customers;
    }

    public Customer fetchCustomerWithId(String id) {
        Customer customer = customers.stream().filter(cust -> cust.getId().equals(id)).findFirst().get();
        System.out.println("Customer ->"+customer);
        return customer;
    }

    public Customer addNewCustomer(Customer customer) {
        String id = String.valueOf(new Date().getTime());
        customer.setId(id);
        customers.add(customer);
        return customer;
    }

    public Order fetchOrders(String id) {
        System.out.println("request id = " + id);
        String baseUrl = env.getProperty("wiremock.url.orderservice");
        Order order = restTemplate.getForObject(baseUrl+"/orders/customers/{customerId}", Order.class,"cust-2232");
        System.out.println("order = " + order);
        return order;
    }
}
