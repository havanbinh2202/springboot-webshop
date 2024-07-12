package com.tn.service;

import com.tn.entity.Account;
import com.tn.repository.Accountrepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface Accountservice extends UserDetailsService {

    // abstract method, public access modifier
    List<Account> getAll();
    Account findByUsername(String username);
}
