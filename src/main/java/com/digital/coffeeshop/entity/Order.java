package com.digital.coffeeshop.entity;

import com.digital.coffeeshop.util.OrderStatusEnum;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  @Id()
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private OrderStatusEnum orderStatus;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "order_item", joinColumns = {
      @JoinColumn(name = "order_id")}, inverseJoinColumns = {
      @JoinColumn(name = "menu_item_id")})
  List<MenuItem> menuItems;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "queue_id", nullable = false)
  private ShopQueue shopQueue;

}