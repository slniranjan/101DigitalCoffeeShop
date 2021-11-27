package com.digital.coffeeshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shop_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopUser {

  @Id()
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "user_role", nullable = false)
  private String role;

  @Column(name = "password")
  @Size(min = 8, max = 15)
  private String password;

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "shop_id", nullable = false)
  private Shop shop;

}
