package com.digital.coffeeshop.controller;

import com.digital.coffeeshop.dto.CustomerDto;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.net.URISyntaxException;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Disabled
public class CustomerControllerIntegrationTest {

//  private final TestRestTemplate testRestTemplate;

  @LocalServerPort
  private int port;

  @Autowired
  TestRestTemplate testRestTemplate;

//  public CustomerControllerIntegrationTest(
//      TestRestTemplate testRestTemplate) {
//    this.testRestTemplate = testRestTemplate;
//  }

  @Test
//  @Sql("/data.sql")
//  @Disabled
  public void registerCustomerTest() throws URISyntaxException {

    final var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    final var customerObject = new JSONObject();
//    customerObject.put("id", 1000L);
    customerObject.put("customerName", "test customer");
    customerObject.put("mobileNumber", "1234567890");
    customerObject.put("address", "Sri Lanka");

//    CustomerDto customerDto = CustomerDto.builder().customerName("test customer")
//        .mobileNumber("1234567890")
//        .address("Sri Lanka").build();

    HttpEntity request = new HttpEntity(customerObject, headers);
//    HttpEntity<CustomerDto> request = new HttpEntity<CustomerDto>(customerDto, headers);
//    String url = "http://localhost:" + port + "/coffeeshop/api/v1/customers";
//    String url = "http://localhost:" + port + "/coffeeshop/api/v1/customers";

    URI url = new URI("http://localhost:" + port + "/coffeeshop/api/v1/customers");
//    JsonNode response = testRestTemplate.postForObject(url, request, JsonNode.class);
    ResponseEntity<CustomerDto> requestEntity = testRestTemplate.postForEntity("/coffeeshop/api/v1/customers/", request, CustomerDto.class);
    System.out.println("#############");
    System.out.println(url);
    System.out.println(requestEntity);

//    CustomerDto createdCustomer = testRestTemplate.postForObject(
//        "http://localhost:".concat(String.valueOf(port)).concat("/coffeeshop/api/v1/customers"),
//        request, CustomerDto.class);

//    assertThat(createdCustomer).isNotNull();
//2    assertThat(createdCustomer.getId()).isEqualTo(1000L);

//    http://localhost:8080/coffeeshop/api/v1/customers
  }

  @Disabled
  @Test
  @Sql("/data.sql")
  public void getCustomerById(){
    ResponseEntity<CustomerDto> requestEntity = testRestTemplate.getForEntity( "http://localhost:" + port +"/coffeeshop/api/v1/customers/1", CustomerDto.class);
    System.out.println(requestEntity);

  }
}
