package com.example.nha_sach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInCTL {
    @GetMapping("/signIn")
    public String SignUp(){
        return "Admin/sign-in";
    }
}
