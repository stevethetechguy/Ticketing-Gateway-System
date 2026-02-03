package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String showLoginPage() {
    	System.out.println("TESTINGNG");
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
}