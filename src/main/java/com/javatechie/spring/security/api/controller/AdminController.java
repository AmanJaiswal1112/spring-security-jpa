package com.javatechie.spring.security.api.controller;

import com.javatechie.spring.security.api.model.User;
import com.javatechie.spring.security.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/rest")

public class AdminController
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/admin/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String addUser(@RequestBody User user)
    {
        String wncodedPass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(wncodedPass);
        userRepository.save(user);
        return "user added ...";
    }
}
