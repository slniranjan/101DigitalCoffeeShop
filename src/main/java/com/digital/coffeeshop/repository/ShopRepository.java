package com.digital.coffeeshop.repository;

import com.digital.coffeeshop.entity.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    
}
