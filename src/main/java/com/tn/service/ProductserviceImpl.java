package com.tn.service;

import com.tn.entity.Product;
import com.tn.repository.Accountrepository;
import com.tn.repository.Productrepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductserviceImpl implements Productservive {

    private Productrepository productrepo;

    public ProductserviceImpl(Productrepository productrepo) {
        this.productrepo = productrepo;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = productrepo.findAll();
        return products;
     }
}

