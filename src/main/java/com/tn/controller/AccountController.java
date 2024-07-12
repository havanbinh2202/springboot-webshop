package com.tn.controller;

import com.tn.entity.Account;
import com.tn.entity.Product;
import com.tn.repository.Accountrepository;
import com.tn.service.Accountservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
@RequestMapping("admin/account")
public class AccountController {

    private Accountservice accountservice;

    private Accountrepository accountrepo;


    @Value(("$abc.t.p.m"))
    private String demo;
    public AccountController(Accountservice accountservice,
                             Accountrepository accountrepo) {
        this.accountservice = accountservice;
        this.accountrepo = accountrepo;
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
                         Pageable pageable){
//        for (int i=0;i<10;i++){
//            sendEmail("helomyfirend"+ i, "heloo"+ i);
//        }

        System.out.println("demo");
        System.out.println(demo);
        System.out.println(pageable);
        Page<Account> pageAccounts = accountrepo.findAll(pageable);
        List<Account> accounts = pageAccounts.toList();
        System.out.println(accounts);

        model.addAttribute("accounts", accounts);
        model.addAttribute("page", "admin-account-list");
        model.addAttribute("totalPage",pageAccounts.getTotalPages());
        model.addAttribute("currentPage",pageable.getPageNumber());
        return "admin-index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id,
                         Model model){
        Optional<Account> opAccount = accountrepo.findById(id);
        if (opAccount.isEmpty()){
            System.out.println("Not found Account with id = " + id);
        }

        accountrepo.deleteById(id);
        List<Account> accounts = accountservice.getAll();
        System.out.println(accounts);

        model.addAttribute("accounts", accounts);

        //return "file-html"
        // return "redirect/path"

        return "redirect:/admin/account";
    }
    @PostMapping("/save")
    public String save(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String email
                       ){
        System.out.println(username);
        System.out.println(email);


        Account account = new Account();
        account.setUsername(username);
        account.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        account.setEmail(email);
//        account.setRole(role);

        accountrepo.save(account);
        return "redirect:/admin/account";

    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("page","admin-account-add");

        return "admin-index";
    }





}
