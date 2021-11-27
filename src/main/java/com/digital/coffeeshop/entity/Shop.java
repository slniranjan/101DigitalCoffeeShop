package com.digital.coffeeshop.entity;

import java.time.LocalTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {

  @Id()
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "number_of_queue", nullable = false)
  private Integer numberOfQueue;

  @Column(name = "contact_details", nullable = false)
  private String contactDetails;

  @Column(name = "open_time", columnDefinition = "TIME", nullable = false)
  private LocalTime openTime;

  @Column(name = "close_time", columnDefinition = "TIME", nullable = false)
  private LocalTime closeTime;

  @Column(name = "latitude", nullable = false)
  private Float latitude;

  @Column(name = "longitude", nullable = false)
  private Float longitude;

  @Column(name = "avg_order_process_time", nullable = false)
  private Integer orderProcessingTime;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "shop_menu", joinColumns = {
      @JoinColumn(name = "shop_id")}, inverseJoinColumns = {
      @JoinColumn(name = "menu_item_id")})
  Set<MenuItem> shopMenus;


}
