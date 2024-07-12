package com.tn.service;

import com.tn.entity.Account;
import com.tn.entity.Product;
import com.tn.repository.Accountrepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Account account = accountrepo.findByUsername(username);

        if (account == null){
            throw new UsernameNotFoundException("Account not found");
        }

        // Collections.emptylist(): cứ đăng nhập đúng username, password là dc
        // k quan trong quyền nên để emptylist
//        return new User(username,account.getPassword(), Collections.emptyList());

        //phân quyền: role = admin hoặc role = user

        List<GrantedAuthority> listRole = new ArrayList<>();
        listRole.add(new SimpleGrantedAuthority(account.getRole()));

        return new User(username, account.getPassword(), listRole);
    }
    public Account findByUsername(String username) {
        return accountrepo.findByUsername(username);
    }


}
