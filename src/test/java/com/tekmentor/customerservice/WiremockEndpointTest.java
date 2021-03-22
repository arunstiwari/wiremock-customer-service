package com.tekmentor.customerservice;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WiremockEndpointTest {

    WireMockServer server;

    @BeforeEach
    public void setup(){
        server = new WireMockServer(8081);

        server.start();
        server.startRecording("http://localhost:8092");
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

    @AfterEach
    public void tearDown(){
        server.stopRecording();
        server.stop();
    }
}
