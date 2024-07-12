package com.tn.service;

import com.tn.entity.Category;
import com.tn.entity.Product;
import com.tn.repository.Productrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface Productservive {
    List<Product> getAll();


}
