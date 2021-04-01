package com.tekmentor.customerservice.service;

import com.tekmentor.customerservice.model.Customer;
import com.tekmentor.customerservice.model.Order;
import com.tekmentor.customerservice.model.ShippingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    private final static Logger LOG = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;
    private List<Customer> customers = new ArrayList<>();

    public  CustomerService() {}

    public List<Customer> fetchAll() {
        return customers;
    }

    public Customer fetchCustomerWithId(String id) {
        Customer customer = customers.stream().filter(cust -> cust.getId().equals(id)).findFirst().get();
        LOG.info("Customer: {}",customer);
        return customer;
    }

    public Customer addNewCustomer(Customer customer) {
        String id = String.valueOf(new Date().getTime());
        customer.setId(id);
        customers.add(customer);
        String orderServiceUrl = env.getProperty("orderservice.url");
        Order o = new Order("ord-"+id, new Date(), id, 500.0);
        Order order = restTemplate.postForObject(orderServiceUrl,o, Order.class);
        LOG.info("Order Posted to Order service:  {}",order);
        return customer;
    }

    public Order fetchOrders(String customerId) {
        LOG.info("customerId = {}",customerId);
        String orderServiceUrl = env.getProperty("orderservice.url.customerid");
        LOG.info("orderServiceUrl = {}",orderServiceUrl);
        String shippingServiceUrl = env.getProperty("shippingservice.url.orderid");
        LOG.info("shippingServiceUrl = {}" , shippingServiceUrl);

        Order order = restTemplate.getForObject(orderServiceUrl, Order.class,customerId);
        LOG.info("order id = {} " , order.getId());
        ShippingStatus status = restTemplate.getForObject(shippingServiceUrl,ShippingStatus.class, order.getId());

        order.setOrderStatus(status);
        LOG.info("order  = {} " , order);
        return order;
    }

    public ShippingStatus fetchStatusForGivenOrderId(String orderId) {
        LOG.info("shipping order request id = {}" , orderId);
        String baseUrl = env.getProperty("shippingservice.url.orderid");
        ShippingStatus shippingStatus = restTemplate.getForObject(baseUrl, ShippingStatus.class,orderId);
        LOG.info("order = {}" , shippingStatus);
        return shippingStatus;
    }
}
