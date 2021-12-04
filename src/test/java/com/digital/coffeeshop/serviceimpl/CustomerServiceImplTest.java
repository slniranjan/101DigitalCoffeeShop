package com.digital.coffeeshop.serviceimpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.digital.coffeeshop.entity.Customer;
import com.digital.coffeeshop.repository.CustomerRepository;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Disabled
class CustomerServiceImplTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerServiceImpl customerService;

  @Test
  void When_SaveCustomerWithGivenDetails_Expect_CustomerDetailsWithId() {

    Customer givenCustomer = Customer.builder().customerName("Niranjan").mobileNumber("1234567890")
        .address("SL").build();
    Customer savedCustomer = Customer.builder().id(1L).customerName("Niranjan")
        .mobileNumber("1234567890").address("SL").build();

    given(customerRepository.save(any(Customer.class))).willReturn(savedCustomer);

    Customer customer = customerService.registerCustomer(givenCustomer);

    then(customerRepository).should().save(givenCustomer);
    assertThat(customer).isEqualTo(savedCustomer);

  }

  @Test
  void When_ExistCustomerIdGiven_Expect_CustomerDetailsOfGivenId() {

    final var givenCustomer = Optional.ofNullable(
        Customer.builder().id(1L).customerName("Niranjan").mobileNumber("1234567890").address("SL")
            .build());

    given(customerRepository.findById(1L)).willReturn(givenCustomer);

    Optional<Customer> customer = customerService.getCustomer(1L);

    then(customerRepository).should().findById(1L);
    assertThat(givenCustomer).isEqualTo(customer);

  }

  @Test
  void When_NonExistCustomerIdGiven_Expect_EmptyCustomer() {

    given(customerRepository.findById(1L)).willReturn(Optional.empty());

    Optional<Customer> customer = customerService.getCustomer(1L);

    then(customerRepository).should().findById(1L);
    assertThat(Optional.empty()).isEqualTo(customer);

  }

}