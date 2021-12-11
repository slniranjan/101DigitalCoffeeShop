package com.digital.coffeeshop.serviceimpl;

import com.digital.coffeeshop.entity.Customer;
import com.digital.coffeeshop.repository.CustomerRepository;
import com.digital.coffeeshop.service.CustomerService;
import java.util.List;
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

  @Override
  public List<Customer> getAllCustomers() {
//    This method ideally call to Keycloack admin API to get all users. but for this poc I will return
//    mock user list
    return List.of(Customer.builder().id(2L).customerName("Mock customer 1").mobileNumber("9543215678")
            .address("Useraddress 1").build(),
        Customer.builder().id(3L).customerName("Mock customer 2").mobileNumber("2134567890")
            .address("User address 2").build());
  }
}
