package com.tn.controller;


import com.tn.entity.Category;
import com.tn.entity.Post;
import com.tn.entity.Product;
import com.tn.repository.Postrepository;
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
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/post")
public class PostController {
    Postrepository postrepo;

    public PostController(Postrepository postrepo) {
        this.postrepo = postrepo;
    }
    @GetMapping()
    public String getAll(Model model){
        List<Post> posts = postrepo.findAll();

        model.addAttribute("post", posts);
        model.addAttribute("page","admin-post-list");
        return "admin-index";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("page","admin-post-add");

        return "admin-index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id,
                         Model model) {
        Optional<Post> opPost = postrepo.findById(id);
        if (opPost.isEmpty()) {
            System.out.println("Not found Post with id = " + id);
        }

        postrepo.deleteById(id);
        List<Post> posts = postrepo.findAll();
        System.out.println(posts);

        model.addAttribute("posts", posts);

        //return "file-html"
        // return "redirect/path"

        return "redirect:/admin/post";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,
                       Model model){
        Optional<Post> opPost = postrepo.findById(id);
        if (opPost.isEmpty()){
            System.out.println("Not found Post with id = " + id);
        }

        Post post = opPost.get();

//        List<Category> categories = categoryrepo.findAll();
//
//        model.addAttribute("product", product);
        model.addAttribute("post", post);
        model.addAttribute("page","admin-post-edit");

        return "admin-index";
    }

    @PostMapping("/save")
    public String save(@RequestParam String title,
                       @RequestParam String content,
                       @RequestParam String author,
                       @RequestParam MultipartFile image){
        System.out.println(title);
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

        Post post = new Post();
        post.setTitle(title);
        post.setAuthor(author);
        post.setContent(content);
        post.setImage(imageName);

        postrepo.save(post);
        return "redirect:/admin/post";

    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id,
                         @RequestParam String title,
                         @RequestParam String author,
                         @RequestParam String content,
                         @RequestParam MultipartFile image){
        Optional<Post> opPost = postrepo.findById(id);
        if (opPost.isEmpty()){
            System.out.println("Not found Post with id = " + id);
        }

        Post post = opPost.get();
        String imageName = image.getOriginalFilename();

        post.setTitle(title);
        post.setAuthor(author);
        post.setContent(content);
        post.setImage(imageName);

        postrepo.save(post);
        return "redirect:/admin/post";
    }
    @GetMapping("/view/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Optional<Post> opPost = postrepo.findById(id);
        if (opPost.isEmpty()) {
            System.out.println("Not found Post with id = " + id);
            return "redirect:/admin/post";
        }

        Post post = opPost.get();
        model.addAttribute("post", post);
        model.addAttribute("page", "admin-post-view");

        return "admin-index";
    }
}
