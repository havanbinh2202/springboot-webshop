package com.tn.controller;

import com.tn.dto.ProductShowDTO;
import com.tn.entity.Category;
import com.tn.entity.Post;
import com.tn.entity.Product;
import com.tn.repository.Categoryrepository;
import com.tn.repository.Productrepository;
import com.tn.service.Productservive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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

    private Categoryrepository categoryrepo;

    public ProductController(Productservive productservive, Productrepository productrepo,
                             Categoryrepository categoryrepo) {
        this.productservive = productservive;
        this.productrepo = productrepo;
        this.categoryrepo = categoryrepo;
    }
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private void sendEmail(String subject, String content) {
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo("havanbinh22022003@gmail.com");
            mailMessage.setSubject(subject);
            mailMessage.setText(content);

            // Sending the mail
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            System.out.println("Send email fail");
        }
    }
    @GetMapping
    public String getAll(Model model,
                         Pageable pageable) {

        // Pageable: Nhận trang số bao nhiêu,
        // sắp xếp alpha beta
        // mỗi trang bao nhiêu bản ghi
        System.out.println(pageable);
        Page<Product> pageProducts = productrepo.findAll(pageable);
        List<Product> products = pageProducts.toList();

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
        model.addAttribute("totalPage",pageProducts.getTotalPages());
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("page", "admin-product-list");
        return "admin-index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id,
                         Model model) {
        Optional<Product> opProduct = productrepo.findById(id);
        if (opProduct.isEmpty()) {
            System.out.println("Not found Account with id = " + id);
        }

        productrepo.deleteById(id);
        List<Product> products = productservive.getAll();
        System.out.println(products);

        model.addAttribute("products", products);

        //return "file-html"
        // return "redirect/path"
        sendEmail("Delete Product","Bạn vừa xóa 1 account: " + opProduct.get().getProductname());
        return "redirect:/admin/product";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("page","admin-product-add");

        return "admin-index";
    }

    @PostMapping("/save")
    public String save(@RequestParam String productName,
                       @RequestParam int price,
                       @RequestParam MultipartFile image){
        System.out.println(productName);
        System.out.println(image);

        String uploadDir = "C:/Users/Public/Javapro/demo25_AccountThymeleaf/src/main/resources/static/img/";
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());

        // Lưu trữ hình ảnh vào thư mục static/images
        try {
            Path uploadPath = Paths.get(uploadDir);
            Files.copy(image.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lưu đường dẫn hình ảnh vào cơ sở dữ liệu
        String imagePath = uploadDir + fileName;

        String imageName = image.getOriginalFilename();

        Product product = new Product();
        product.setProductname(productName);
        product.setPrice(price);
        product.setImage(imageName);

        productrepo.save(product);
        return "redirect:/admin/product";

    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,
                       Model model){
        Optional<Product> opProduct = productrepo.findById(id);
        if (opProduct.isEmpty()){
            System.out.println("Not found Product with id = " + id);
        }

        Product product = opProduct.get();

        List<Category> categories = categoryrepo.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("page","admin-product-edit");

        return "admin-index";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id,
                         @RequestParam String productName,
                         @RequestParam int price,
                         @RequestParam MultipartFile image,
                         @RequestParam Integer category_id){
        Optional<Product> opProduct = productrepo.findById(id);
        if (opProduct.isEmpty()){
            System.out.println("Not found Product with id = " + id);
        }


        Product product = opProduct.get();
        String imageName = image.getOriginalFilename();

        product.setProductname(productName);
        product.setPrice(price);
        product.setImage(imageName);

        Category category = categoryrepo.findById(category_id).orElse(null);
        product.setCategory(category);
        productrepo.save(product);
        return "redirect:/admin/product";
    }






}
