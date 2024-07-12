package com.tn.controller;

import com.tn.dto.ProductShowDTO;
import com.tn.entity.*;
import com.tn.repository.Categoryrepository;
import com.tn.repository.Postrepository;
import com.tn.repository.Productrepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientIndexController {

    private Productrepository productRepo;

    private Postrepository postrepo;


    private Categoryrepository categoryrepo;

    public ClientIndexController(Productrepository productRepo,
                                 Postrepository postrepo,
                                 Categoryrepository categoryrepo) {
        this.productRepo = productRepo;
        this.postrepo = postrepo;
        this.categoryrepo = categoryrepo;
    }

    @GetMapping
    public String clientIndex(Model model) {
        List<Post> posts = postrepo.findAll();
        model.addAttribute("post", posts);

        List<Product> products = productRepo.findAll();

        List<ProductShowDTO> productShowDTOS = new ArrayList<>();
        products.forEach(obj -> {
            ProductShowDTO productShowDTO = new ProductShowDTO();
            productShowDTO.setId(obj.getId());
            productShowDTO.setProductname(obj.getProductname());
            productShowDTO.setPrice(obj.getPrice());
            productShowDTO.setImage(obj.getImage());

            if (obj.getCategory() != null)
                productShowDTO.setCategoryname(obj.getCategory().getName());

            productShowDTOS.add(productShowDTO);
        });

        model.addAttribute("products", productShowDTOS);

        String page = "client-index";
        model.addAttribute("page", page);

        return "client-index";
    }

    @GetMapping("/contact")
    public String clientContact(Model model) {

        String page = "client-contact";
        model.addAttribute("page", page);

        return "contact.html";
    }

    @GetMapping("/product")
    public String getAll(Model model) {

        List<Product> products = productRepo.findAll();

        List<ProductShowDTO> productShowDTOS = new ArrayList<>();
        products.forEach(obj -> {
            ProductShowDTO productShowDTO = new ProductShowDTO();
            productShowDTO.setId(obj.getId());
            productShowDTO.setProductname(obj.getProductname());
            productShowDTO.setPrice(obj.getPrice());
            productShowDTO.setImage(obj.getImage());

            if (obj.getCategory() != null)
                productShowDTO.setCategoryname(obj.getCategory().getName());

            productShowDTOS.add(productShowDTO);
        });

        model.addAttribute("products", productShowDTOS);

        String page = "product-list-client";
        model.addAttribute("page", page);

//        return "client-index";
        return "product.html";
    }

    @GetMapping("/product/view/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Optional<Product> opProduct = productRepo.findById(id);
        if (opProduct.isEmpty()) {
            System.out.println("Not found Product with id = " + id);
            return "redirect:/product";
        }

        String page = "product-list-view";
        Product product = opProduct.get();
        model.addAttribute("product", product);
        model.addAttribute("page", page);

//        return "client-index";
        return "product-list-view.html";
    }

    @GetMapping("/post")
    public String getAll1(Model model) {
        List<Post> posts = postrepo.findAll();

        String page = "post-list-client";
        model.addAttribute("post", posts);
        model.addAttribute("page", page);
        return "blog.html";
    }

    @GetMapping("/post/view/{id}")
    public String detail1(@PathVariable Integer id, Model model) {
        Optional<Post> opPost = postrepo.findById(id);
        if (opPost.isEmpty()) {
            System.out.println("Not found Post with id = " + id);
            return "redirect:/post";
        }

        Post post = opPost.get();
        String page = "post-list-view";
        model.addAttribute("post", post);
        model.addAttribute("page", page);

        return "detail.html";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String data) {
        List<Product> products = new ArrayList<>();
        if (data.equals("")) {
            products = productRepo.findAll();
        } else {
            products = productRepo.getData(data);
        }
        model.addAttribute("products", products);
        model.addAttribute("page", "client-index");
        return "client-index";
    }

    @GetMapping("/about")
    public String clientAbout(Model model) {

        String page = "client-about";
        model.addAttribute("page", page);

        return "about.html";
    }

    @GetMapping("/feature")
    public String clientfeature(Model model) {

        String page = "client-feature";
        model.addAttribute("page", page);

        return "feature.html";
    }

    @GetMapping("/testimonial")
    public String clienttestimonial(Model model) {

        String page = "client-testimonial";
        model.addAttribute("page", page);

        return "testimonial.html";
    }
    @GetMapping("/errol")
    public String clienterrol(Model model) {

        String page = "client-errol";
        model.addAttribute("page", page);

        return "404.html";
    }







}
