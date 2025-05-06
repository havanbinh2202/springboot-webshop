package com.tn.controller;

import com.tn.entity.Category;
import com.tn.entity.Product;
import com.tn.repository.Categoryrepository;
import com.tn.service.Categoryservice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        model.addAttribute("categories", categories);
        model.addAttribute("page","admin-category-list");
        return "admin-index";
    }

    @GetMapping("delete/{categoryId}")
    public String delete(@PathVariable Integer categoryId,
                         Model model){
        Optional<Category> opCategory= categoryrepo.findById(categoryId);
        if (opCategory.isEmpty()){
            System.out.println("Not found Account with id = " + categoryId);
        }

        categoryrepo.deleteById(categoryId);
        List<Category> categories = categoryservice.getAll();
        System.out.println(categories);

        model.addAttribute("categories", categories);

        //return "file-html"
        // return "redirect/path"

        return "redirect:/admin/category";
    }

    @GetMapping("/edit/{categoryId}")
    public String edit(@PathVariable Integer categoryId,
                       Model model){
        Optional<Category> opCategory = categoryrepo.findById(categoryId);
        if (opCategory.isEmpty()){
            System.out.println("Not found Category with id = " + categoryId);
        }

        Category category = opCategory.get();

//        List<Category> categories = categoryrepo.findAll();
//
//        model.addAttribute("product", product);
        model.addAttribute("category", category);
        model.addAttribute("page","admin-category-edit");

        return "admin-index";
    }
    @PostMapping("/update/{categoryId}")
    public String update(@PathVariable Integer categoryId,
                         @RequestParam String categoryname
                         ){
        Optional<Category> opCategory = categoryrepo.findById(categoryId);
        if (opCategory.isEmpty()){
            System.out.println("Not found Product with id = " + categoryId);
        }


        Category category = opCategory.get();

        // Cập nhật tên category
        category.setCategoryname(categoryname);

        // Lưu lại thông tin Category đã cập nhật
        categoryrepo.save(category);

        // Chuyển hướng về trang danh sách category
        return "redirect:/admin/category";
    }

}
