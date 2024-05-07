package com.tn.service;

import com.tn.entity.Product;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface Productservive {
    List<Product> getAll();
}
