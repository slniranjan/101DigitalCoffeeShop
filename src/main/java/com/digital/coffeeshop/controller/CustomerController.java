package com.digital.coffeeshop.controller;

import com.digital.coffeeshop.dto.CustomerDto;
import com.digital.coffeeshop.entity.Customer;
import com.digital.coffeeshop.exception.ResourceNotFoundException;
import com.digital.coffeeshop.service.CustomerService;
import com.digital.coffeeshop.util.Constant;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Customer endpoints
 *
 * @author Niranjan Thilakarathna
 */
@RestController
@RequestMapping(path = {"/api/v1/customers"}, produces = Constant.PRODUCE_TYPE)
@Validated
public class CustomerController {

  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  private final CustomerService customerService;

  private final ModelMapper modelMapper;

  @Autowired
  public CustomerController(CustomerService customerService,
      ModelMapper modelMapper) {
    this.customerService = customerService;
    this.modelMapper = modelMapper;
  }

  /**
   * @param customerDto new customer details
   * @return CustomerDto Register/Save new customer
   */
  //  @Operation(summary = "Crate a new order")
//  @ApiResponse(responseCode = "201", description = "Order is created", content = {
//      @Content(mediaType = Constant.PRODUCE_TYPE, schema = @Schema(implementation = CustomerDto.class))})
  @PostMapping(consumes = Constant.PRODUCE_TYPE)
  public ResponseEntity<CustomerDto> registerCustomer(@Valid @RequestBody CustomerDto customerDto) {
    logger.info("Customer creation start:");

    final Customer registerCustomer = customerService.registerCustomer(
        modelMapper.map(customerDto, Customer.class));
    CustomerDto responseCustomerDto = modelMapper.map(registerCustomer, CustomerDto.class);

    final var end = String.format("Customer creation success: %s", responseCustomerDto.toString());
    logger.info(end);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseCustomerDto);

  }

  /**
   * @param customerId - search id of the customer
   * @return CutomerDto Return customer details by given customer id
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long customerId) {
    logger.info("Get customer by id started:");

    final Customer customer = customerService.getCustomer(customerId).orElseThrow(
        () -> new ResourceNotFoundException("Not found customer with id: " + customerId));

    final var end = String.format("Customer creation success: %s", customer.toString());
    logger.info(end);

    return ResponseEntity.ok(modelMapper.map(customer, CustomerDto.class));

  }
}
