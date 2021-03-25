package com.tekmentor.customerservice.service;

import com.tekmentor.customerservice.model.Customer;
import com.tekmentor.customerservice.model.Order;
import com.tekmentor.customerservice.model.ShippingStatus;
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

    public Order fetchOrders(String customerId) {
        System.out.println("customerId = " + customerId);
        String orderServiceUrl = env.getProperty("orderservice.url.customerid");
        System.out.println("orderServiceUrl = " + orderServiceUrl);
        String shippingServiceUrl = env.getProperty("shippingservice.url.orderid");
        System.out.println("shippingServiceUrl = " + shippingServiceUrl);

        Order order = restTemplate.getForObject(orderServiceUrl, Order.class,customerId);
        System.out.println("order id = " + order.getId());
        ShippingStatus status = restTemplate.getForObject(shippingServiceUrl,ShippingStatus.class, order.getId());

        order.setOrderStatus(status);
        System.out.println("order  = " + order);
        return order;
    }

    public ShippingStatus fetchStatusForGivenOrderId(String orderId) {
        System.out.println("shipping order request id = " + orderId);
        String baseUrl = env.getProperty("shippingservice.url.orderid");
        ShippingStatus shippingStatus = restTemplate.getForObject(baseUrl, ShippingStatus.class,orderId);
        System.out.println("order = " + shippingStatus);
        return shippingStatus;
    }

}
