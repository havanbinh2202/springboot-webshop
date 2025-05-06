package com.tn.controller;

import com.tn.entity.Order;
import com.tn.model.Item;
import com.tn.service.CartService;
import com.tn.service.Productservice;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    private CartService cartService;
    private Productservice productService;

    public CartController(CartService cartService, Productservice productService) {
        this.cartService = cartService;
        this.productService = productService;
    }
    @GetMapping("/cart")
    public String showcart(Model model, HttpSession session) {
        List<Item> items = (List<Item>) session.getAttribute("cartitems");
        if (items == null) items = new ArrayList<>();
        model.addAttribute("cartitems", items);
        double total = items.stream().mapToDouble(Item::getTotal).sum();
        model.addAttribute("totalCart", total);
        return "shopingcart";
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/addcart/{productId}")
    public String addcart(@PathVariable("productId") Long productId, Model model, HttpSession session) {
        List<Item> items=new ArrayList<>();
        if(session.getAttribute("cartitems")!=null)
        {
            items=(List<Item>)session.getAttribute("cartitems");
        }
        items=cartService.addCart(productService.getById(productId),items);
        session.setAttribute("cartitems", items);
        model.addAttribute("cartitems",items);
        Double total=items.stream().mapToDouble(Item::getTotal).sum();
        model.addAttribute("totalCart",total);
        return "shopingcart";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/removeCart/{id}")
    public String removeCart(@PathVariable("id") Long productId, Model model, HttpSession session) {
        List<Item> items = (List<Item>) session.getAttribute("cartitems");
        if (items == null) items = new ArrayList<>();

        items= cartService.removeCart(productId,items);
        session.setAttribute("cartitems", items);
        model.addAttribute("cartitems",items);
        Double total=items.stream().mapToDouble(Item::getTotal).sum();
        model.addAttribute("totalCart",total);
        System.out.println("Đang xóa sản phẩm: " + productId);
        return "redirect:/cart";
    }
    @SuppressWarnings("unchecked")
    @PostMapping("/updateCart")
    public String updateCart(@RequestParam Map<String, Integer> updates, Model model, HttpSession session) {
        List<Item> items=new ArrayList<>();
        if(session.getAttribute("cartitems")!=null)
        {
            items=(List<Item>)session.getAttribute("cartitems");
        }
        items=cartService.updateCart(updates, items);

        session.setAttribute("cartitems", items);
        model.addAttribute("cartitems",items);
        Double total=items.stream().mapToDouble(Item::getTotal).sum();
        model.addAttribute("totalCart",total);
        return "shopingcart";
    }
    @SuppressWarnings("unchecked")
    @PostMapping("/checkout")
    public String checkout(Order order, Model model, HttpSession session) {
        List<Item> items=new ArrayList<>();
        if(session.getAttribute("cartitems")!=null)
        {
            items=(List<Item>)session.getAttribute("cartitems");
        }
        String msg=cartService.insertOrder(order,items);
        model.addAttribute("msg",msg);
        session.setAttribute("cartitems", new ArrayList<>());
        return "redirect:/";
    }
}
