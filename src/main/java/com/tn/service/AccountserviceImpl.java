package com.tn.service;

import com.tn.entity.Account;
import com.tn.repository.Accountrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountserviceImpl implements Accountservice {
//  c1 :   @Autowired
        private Accountrepository accountrepo;

        // c2:
        public AccountserviceImpl(Accountrepository accountrepo) {
            this.accountrepo = accountrepo;
        }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = accountrepo.findAll();
        return accounts;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountrepo.getDataUsername(username);

        if (account == null){
            throw new UsernameNotFoundException("Account not found");
        }

        return new User(username,account.getPassword(), Collections.emptyList());
    }
}
