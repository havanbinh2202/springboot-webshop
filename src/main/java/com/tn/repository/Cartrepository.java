package com.tn.repository;

import com.tn.entity.Account;
import com.tn.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Cartrepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByAccount(Account account);
}
