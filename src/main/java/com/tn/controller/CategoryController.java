package com.tn.controller;

import com.tn.entity.Category;
import com.tn.entity.Product;
import com.tn.repository.Categoryrepository;
import com.tn.repository.Productrepository;
import com.tn.service.Categoryservice;
import com.tn.service.Productservive;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/category")
public class CategoryController {
    private Categoryservice categoryservice;

    private Categoryrepository categoryrepo;

    public CategoryController(Categoryservice categoryservice, Categoryrepository categoryrepo) {
        this.categoryservice = categoryservice;
        this.categoryrepo = categoryrepo;
    }
    @GetMapping
    public String getAll(Model model){
        List<Category> categories = categoryservice.getAll();
        System.out.println(categories);

        model.addAttribute("categories", categories);
        return "Categorylist";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id,
                         Model model){
        Optional<Category> opCategory= categoryrepo.findById(id);
        if (opCategory.isEmpty()){
            System.out.println("Not found Account with id = " + id);
        }

        categoryrepo.deleteById(id);
        List<Category> categories = categoryservice.getAll();
        System.out.println(categories);

        model.addAttribute("categories", categories);

        //return "file-html"
        // return "redirect/path"

        return "redirect:/admin/category";
    }
}
