package com.tn.repository;

import com.tn.entity.Account;
import com.tn.entity.Category;
import com.tn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Productrepository extends JpaRepository<Product, Integer> {

//    @Query(value = "FROM Product where productname = :productname")
//    Account getDataProductName(String productname);

    @Query(value = "FROM Product  where productname like concat('%', :productname, '%')")
    List<Product> getData(String productname);



}
