package com.digital.coffeeshop.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column(name = "number_of_queue", nullable = false)
    private Integer numberOfQueue;

    @Column(name = "contact_details", nullable = false)
    private String contactDetails;

    @Column(name = "open_time", columnDefinition = "TIME", nullable = false)
    private LocalTime openTime;

    @Column(name = "close_time", columnDefinition = "TIME", nullable = false)
    private LocalTime closeTime;

    @Column(name = "gps_location", nullable = false)
    private Long gpsLocation;
}
