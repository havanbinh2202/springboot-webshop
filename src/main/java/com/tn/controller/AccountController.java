package com.tn.controller;

import com.tn.entity.Account;
import com.tn.repository.Accountrepository;
import com.tn.service.Accountservice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/account")
public class AccountController {

    private Accountservice accountservice;

    private Accountrepository accountrepo;


    public AccountController(Accountservice accountservice,
                             Accountrepository accountrepo) {
        this.accountservice = accountservice;
        this.accountrepo = accountrepo;
    }

    @GetMapping
    public String getAll(Model model){
        List<Account> accounts = accountservice.getAll();
        System.out.println(accounts);

        model.addAttribute("accounts", accounts);
        return "Accountlist";
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



}
