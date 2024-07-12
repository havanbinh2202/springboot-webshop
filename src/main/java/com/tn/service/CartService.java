package com.tn.service;

import com.tn.entity.Account;
import com.tn.entity.Cart;
import com.tn.entity.Product;
import com.tn.repository.Accountrepository;
import com.tn.repository.Cartrepository;
import com.tn.repository.Productrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private Cartrepository cartrepo;

    @Autowired
    private Productrepository productrepo;

    @Autowired
    private Accountrepository accountrepo;

    public Cart getCartByAccount(Account account) {
        return cartrepo.findByAccount(account).orElseGet(() -> {
            Cart cart = new Cart();
            cart.setAccount(account);
            return cartrepo.save(cart);
        });
    }

    public void addProductToCart(int accountId, int productId, int quantity) {
        Optional<Account> accountOptional = accountrepo.findById(accountId);
        Optional<Product> productOptional = productrepo.findById(productId);

        if (accountOptional.isPresent() && productOptional.isPresent()) {
            Account account = accountOptional.get();
            Product product = productOptional.get();
            Cart cart = getCartByAccount(account);
            cart.addItem(product, quantity);
            cartrepo.save(cart);
        }
        else {
            System.out.println("Account or Product not found!");
        }
    }

    public void removeProductFromCart(int accountId, int productId) {
        Optional<Account> accountOptional = accountrepo.findById(accountId);
        Optional<Product> productOptional = productrepo.findById(productId);

        if (accountOptional.isPresent() && productOptional.isPresent()) {
            Account account = accountOptional.get();
            Product product = productOptional.get();
            Cart cart = getCartByAccount(account);
            cart.removeItem(product);
            cartrepo.save(cart);
        }
    }
}
