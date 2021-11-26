package com.digital.coffeeshop.repository;

import com.digital.coffeeshop.entity.ShopUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser, Long>{
    
}
