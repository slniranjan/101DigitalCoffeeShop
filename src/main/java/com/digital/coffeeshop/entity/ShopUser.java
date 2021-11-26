package com.digital.coffeeshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Shop shop;
}
