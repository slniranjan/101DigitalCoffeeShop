package com.digital.coffeeshop.repository;

import com.digital.coffeeshop.entity.ShopQueue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopQueueRepository extends JpaRepository<ShopQueue, Long> {

}
