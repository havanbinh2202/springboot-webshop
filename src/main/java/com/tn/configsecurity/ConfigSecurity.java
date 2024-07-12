package com.tn.configsecurity;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;


@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    private String[] arrPath = {"/", "/product", "/img/**", "/css/**", "/js/**", "/lib/**", "/scss/**","/signup","/search","/feature","/about","/contact","/testimonial","/post","/errol"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf().disable();

        // path này dc qa hết
        httpSecurity.authorizeRequests().requestMatchers(arrPath).permitAll();


        // muốn vào path admin cần có role admin
        httpSecurity.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN");

        // tất cả request đều phải đăng nhập
        httpSecurity.authorizeRequests().anyRequest().authenticated();



        // mặc định, /login vào form login spring
        //
        httpSecurity.formLogin()
                .loginPage("/login").permitAll();
        return httpSecurity.build();
    }
}
