package com.digital.coffeeshop.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.digital.coffeeshop.dto.CustomerDto;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Disabled
public class CustomerControllerIntegrationTest {

  private final TestRestTemplate testRestTemplate;

  @LocalServerPort
  private int port;

  @Autowired
  public CustomerControllerIntegrationTest(
      TestRestTemplate testRestTemplate) {
    this.testRestTemplate = testRestTemplate;
  }

  @Test
  public void registerCustomerTest() {

    final var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    final var customerObject = new JSONObject();
    customerObject.put("id", 1000L);
    customerObject.put("customerName", "test customer");
    customerObject.put("mobileNumber", "1234567890");
    customerObject.put("address", "Sri Lanka");

    HttpEntity<String> request = new HttpEntity<>(customerObject.toString(), headers);

    CustomerDto createdCustomer = testRestTemplate.postForObject(
        "http://localhost:".concat(String.valueOf(port)).concat("/coffeeeshop/api/v1/customer"),
        request, CustomerDto.class);

    assertThat(createdCustomer).isNotNull();
    assertThat(createdCustomer.getId()).isEqualTo(1000);
  }
}
