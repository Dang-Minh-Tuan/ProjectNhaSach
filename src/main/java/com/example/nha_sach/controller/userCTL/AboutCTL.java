package com.example.nha_sach.controller.userCTL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutCTL {
    @GetMapping("/about")
    public String getHome(){
        return "User/about";
    }
}
