package com.tn.repository;

import com.tn.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemrepository extends JpaRepository<CartItem, Integer> {
}
