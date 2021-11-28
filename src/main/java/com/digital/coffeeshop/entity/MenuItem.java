package com.digital.coffeeshop.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_item", uniqueConstraints = @UniqueConstraint(name = "item_name_unique", columnNames = "item_name"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

  @Id()
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "item_name")
  private String name;

  @Column(name = "price")
  private Double price;

  @Column(name = "description")
  private String description;

  @ManyToMany(mappedBy = "shopMenus")
  private Set<Shop> shops;

  @ManyToMany(mappedBy = "menuItems")
  private Set<Order> orders;

}
