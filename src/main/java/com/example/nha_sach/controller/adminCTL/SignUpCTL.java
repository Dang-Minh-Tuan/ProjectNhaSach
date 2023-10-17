package com.example.nha_sach.controller.adminCTL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpCTL {
    @GetMapping("/signUp")
    public String SignUp(){
        return "Admin/sign-up";
    }
}
