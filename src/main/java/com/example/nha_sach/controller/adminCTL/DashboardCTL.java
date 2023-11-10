package com.example.nha_sach.controller.adminCTL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardCTL {
    @GetMapping("/dashboard")
    public String home(){
        return "admin/dashboard";

    }
}
