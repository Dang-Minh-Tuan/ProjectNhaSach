package com.example.nha_sach.controller.userCTL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StyleCTL {
    @GetMapping("/styles")
    public String getHome(){
        return "User/styles";
    }
}
