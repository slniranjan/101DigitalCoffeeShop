package com.digital.coffeeshop.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.digital.coffeeshop.Constant;
import com.digital.coffeeshop.ErrorResponseBody;
import com.digital.coffeeshop.dto.CustomerDto;
import com.digital.coffeeshop.entity.Customer;
import com.digital.coffeeshop.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureJsonTesters
@WebMvcTest(CustomerController.class)
@Disabled
class CustomerControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CustomerService customerService;

  @SpyBean
  private ModelMapper modelMapper;

  @Autowired
  private JacksonTester<CustomerDto> customerDto;

  private final String CREATE_CUSTOMER = "/api/v1/customers";

  @Test
  void When_ValidCustomerDataGiven_Expect_CustomerCreateSuccess() throws Exception {

    given(customerService.registerCustomer(any(Customer.class))).willReturn(
        Customer.builder().id(1L).customerName("Ninja").mobileNumber("1234567890").address("SL")
            .build());

    MockHttpServletResponse response = mvc.perform(
            post(CREATE_CUSTOMER)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(customerDto.write(new CustomerDto(1L, "Ninja", "1234567890", "SL")).getJson()))
        .andDo(print()).andReturn().getResponse();

    assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.getContentAsString()).isEqualTo(customerDto.write(CustomerDto.builder()
        .id(1L).customerName("Ninja").mobileNumber("1234567890").address("SL").build()).getJson());
  }

  @Test
  void When_InvalidCustomerDataGiven_Expect_MethodArgumentNotValidException() throws Exception {

    MockHttpServletResponse response = mvc.perform(
            post(CREATE_CUSTOMER)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{}"))
        .andDo(print()).andReturn().getResponse();

    assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    ObjectMapper objectMapper = new ObjectMapper();
    ErrorResponseBody body = objectMapper.readValue(response.getContentAsString(),
        ErrorResponseBody.class);

    assertThat(body.getType()).isEqualTo(Constant.METHOD_ARGUMENT_NOT_VALID);

  }

  @Test
  void When_GetHttpMethodCallToRegisterCustomer_Expect_MethodNotSupportedException()
      throws Exception {

    MockHttpServletResponse response = mvc.perform(
            get(CREATE_CUSTOMER)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(customerDto.write(new CustomerDto(1L, "Ninja", "1234567890", "SL")).getJson()))
        .andDo(print()).andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED.value());

  }

  @Test
  void When_ExistCustomerIdGiven_Expect_CorrectDetailsOfTheCustomer() throws Exception {

    given(customerService.getCustomer(1L))
        .willReturn(java.util.Optional.of(new Customer(1L, "ABC", "1234567890", "SL")));

    MockHttpServletResponse response = mvc.perform(
            get(CREATE_CUSTOMER.concat("/1"))
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andReturn().getResponse();

    assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(
        customerDto.write(new CustomerDto(1L, "ABC", "1234567890", "SL")).getJson()
    );

  }

  @Test
  void When_NonExistCustomerIdGiven_Expect_ResourceNotFoundException() throws Exception {

//    when(customerService.getCustomer(eq(2L))).thenThrow(ResourceNotFoundException.class);
    given(customerService.getCustomer(1L))
        .willReturn(java.util.Optional.of(new Customer(1L, "ABC", "1234567890", "SL")));

    MockHttpServletResponse response = mvc.perform(
            get(CREATE_CUSTOMER.concat("/2"))
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andReturn().getResponse();

    assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    ObjectMapper objectMapper = new ObjectMapper();
    ErrorResponseBody body = objectMapper.readValue(response.getContentAsString(),
        ErrorResponseBody.class);

    assertThat(body.getType()).isEqualTo(Constant.NOT_FOUND);

  }
}