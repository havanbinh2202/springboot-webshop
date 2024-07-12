package com.tn.controller;


import com.tn.entity.Account;
import com.tn.repository.Accountrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AccountClientController {
//    @GetMapping("client/login")
//    public String accountClientLogin(){
//        return "client-login";
//    }
    private Accountrepository accountrepo;
    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder bean


    public AccountClientController(Accountrepository accountrepo) {
        this.accountrepo = accountrepo;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup_save")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String email,
                         @RequestParam(required = false) String role, // Optional role parameter
                         Model model) {
        // Check for existing username
        Account existingAccount = accountrepo.findByUsername(username);

        if (existingAccount != null) {
            model.addAttribute("message", "Tài khoản đã tồn tại (username)!");
            return "signup"; // Remain on signup page with error message
        }


        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));

        account.setEmail(email);

        if (role == null || role.isEmpty()) {
            account.setRole("ROLE_USER"); // Set to ROLE_USER if no role provided
        } else {
            account.setRole(role); // Use provided role if available
        }

        model.addAttribute("message", "đăng ký thành công!");
        accountrepo.save(account);

        return "redirect:/login"; // Chuyển hướng khi đăng ký thành công

    }
    @GetMapping("/user-info")
    public String userInfo(Principal principal, Model model) {
        // Lấy thông tin người dùng hiện tại
        String username = principal.getName();
        Account account = accountrepo.findByUsername(username);

        // Thêm thông tin người dùng vào model
        model.addAttribute("username", account.getUsername());
        model.addAttribute("email", account.getEmail());
        model.addAttribute("role", account.getRole());

        return "user-info"; // Tên view của form thông tin người dùng
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        return "change-password"; // Tên view của trang đổi mật khẩu
    }

    @PostMapping("/change-password-save")
    public String changePasswordSave(@RequestParam String currentPassword,
                                     @RequestParam String newPassword,
                                     Model model,
                                     Principal principal) {
        // Lấy thông tin người dùng hiện tại
        String username = principal.getName();
        Account account = accountrepo.findByUsername(username);

        // Xác thực mật khẩu hiện tại
        if (!passwordEncoder.matches(currentPassword, account.getPassword())) {
            model.addAttribute("error", "Mật khẩu hiện tại không chính xác!");
            return "change-password";
        }

        // Cập nhật mật khẩu mới
        account.setPassword(passwordEncoder.encode(newPassword));
        accountrepo.save(account);

        model.addAttribute("message", "Đổi mật khẩu thành công!");
        return "change-password-save";
    }

}


// Set default ROLE_USER if role is not provided

//
//        try {
//            accountrepo.save(account); // Attempt to save the account
//            String message = "User created successfully!";
//            return ResponseEntity.ok(message);
//        } catch (Exception e) {
//            e.printStackTrace(); // Log the exception for debugging
//            return ResponseEntity.internalServerError().body("Error creating user.");
//        }
