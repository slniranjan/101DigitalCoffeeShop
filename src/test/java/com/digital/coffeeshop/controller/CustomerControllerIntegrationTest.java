package com.digital.coffeeshop.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.digital.coffeeshop.dto.CustomerDto;
import com.digital.coffeeshop.entity.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
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
  public void registerCustomerTest(){
   Customer customer = Customer.builder().id(1000L).mobileNumber("1234567891").customerName("testcustomer").address("Sri Lanka, Kegalle").build();
    HttpEntity<Customer> request = new HttpEntity<>(customer);

    CustomerDto createdCustomer = testRestTemplate.postForObject("http://localhost:".concat(String.valueOf(port)).concat("/coffeeeshop/api/v1/customer"), customer, CustomerDto.class);

    assertThat(createdCustomer).isNotNull();
    assertThat(createdCustomer.getId()).isEqualTo(1000);
  }
}
