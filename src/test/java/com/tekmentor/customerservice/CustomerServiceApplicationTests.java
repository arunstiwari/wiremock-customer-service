package com.tekmentor.customerservice;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class CustomerServiceApplicationTests {

    @Test
    void fetchAllCustomers() {
        given()
                .log().all()
        .when()
            .get("http://localhost:8084/customers")
        .then()
            .statusCode(200)
        .log().all();
    }

    @Test
    void addNewCustomer(){
        given()
            .log().all()
            .header("Content-type", "application/json")
            .body("{\"name\": \"Customer1\", \"age\": \"34\"}")
        .when()
            .post("http://localhost:8084/customers")
        .then()
            .statusCode(201);
    }

    @Test
    void fetchOrdersForGivenCustomer() {
        given()
                .log().all()
                .when()
                .get("http://localhost:8084/customers/{id}/orders","cust-2232")
                .then()
                .statusCode(200)
                .log().all();
    }
}

