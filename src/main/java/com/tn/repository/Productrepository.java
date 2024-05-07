package com.tn.repository;

import com.tn.entity.Account;
import com.tn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Productrepository extends JpaRepository<Product, Integer> {

//    @Query(value = "FROM Product where productname = :productname")
//    Account getDataProductName(String productname);
}
