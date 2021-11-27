package com.digital.coffeeshop.service;

import com.digital.coffeeshop.entity.Customer;
import java.util.Optional;

public interface CustomerService {

  Customer registerCustomer(Customer customer);

  Optional<Customer> getCustomer(Long customerId);
}
