package com.example.nha_sach.controller.userCTL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetailProductCTL {
    @GetMapping("/detailProduct")
    public String getHome(){
        return "user/single-product";
    }
}
