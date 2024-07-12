package com.tn.repository;

import com.tn.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Accountrepository extends JpaRepository<Account, Integer> {
 // c1:    Account findByUsername(String username);

//    @Query(value = "FROM Account where username = :username")
//    Account getDataUsername(String username);
    Account findByUsername(String username);
    Account findByEmail(String email);

}
