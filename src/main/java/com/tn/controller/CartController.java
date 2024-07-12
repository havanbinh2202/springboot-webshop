package com.tn.controller;

import com.tn.entity.Account;
import com.tn.entity.Cart;
import com.tn.service.Accountservice;
import com.tn.service.CartService;
import com.tn.service.Productservive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private Productservive productService;

    @Autowired
    private Accountservice accountService;

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        String username = principal.getName();
        Account account = accountService.findByUsername(username);
        Cart cart = cartService.getCartByAccount(account);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam int productId,
                            @RequestParam int quantity,
                            Principal principal) {
        String username = principal.getName();
        Account account = accountService.findByUsername(username);
        System.out.println("Account: " + account);
        System.out.println("ProductId: " + productId + ", Quantity: " + quantity);
        if (account != null) {
            cartService.addProductToCart(account.getId(), productId, quantity);
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam int productId, Principal principal) {
        String username = principal.getName();
        Account account = accountService.findByUsername(username);
        cartService.removeProductFromCart(account.getId(), productId);
        return "redirect:/cart";
    }
}
