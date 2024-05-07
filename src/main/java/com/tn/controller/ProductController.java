package com.tn.controller;

import com.tn.entity.Account;
import com.tn.entity.Product;
import com.tn.repository.Accountrepository;
import com.tn.repository.Productrepository;
import com.tn.service.Accountservice;
import com.tn.service.Productservive;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/product")
public class ProductController {

//    @GetMapping("product")
//    public String getAll(Model model){
//        String page = "product-list-client";
//        model.addAttribute("page",page);
//
//        return "client-index";
//    }
    private Productservive productservive;

    private Productrepository productrepo;

    public ProductController(Productservive productservive, Productrepository productrepo) {
        this.productservive = productservive;
        this.productrepo = productrepo;
    }
    @GetMapping
    public String getAll(Model model){
        List<Product> products = productservive.getAll();
        System.out.println(products);

        model.addAttribute("products", products);
        return "Productlist";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id,
                         Model model){
        Optional<Product> opProduct= productrepo.findById(id);
        if (opProduct.isEmpty()){
            System.out.println("Not found Account with id = " + id);
        }

        productrepo.deleteById(id);
        List<Product> products = productservive.getAll();
        System.out.println(products);

        model.addAttribute("products", products);

        //return "file-html"
        // return "redirect/path"

        return "redirect:/admin/product";
    }
}
