package com.digital.coffeeshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(name = "mobile_number_unique", columnNames = "mobile_number"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Customer {

  @Id()
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String customerName;

  @Column(name = "mobile_number", nullable = false)
  private String mobileNumber;

  @Column(name = "address")
  private String address;

}
