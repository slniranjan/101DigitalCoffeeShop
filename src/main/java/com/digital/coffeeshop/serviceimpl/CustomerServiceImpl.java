package com.digital.coffeeshop.serviceimpl;

import com.digital.coffeeshop.entity.Customer;
import com.digital.coffeeshop.repository.CustomerRepository;
import com.digital.coffeeshop.service.CustomerService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(
      CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer registerCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public Optional<Customer> getCustomer(Long customerId) {
    return customerRepository.findById(customerId);
  }
}
