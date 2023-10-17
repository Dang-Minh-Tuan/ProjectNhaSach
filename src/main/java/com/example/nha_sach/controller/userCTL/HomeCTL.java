package com.example.nha_sach.controller.userCTL;

import com.example.nha_sach.dto.ProductDTO;
import com.example.nha_sach.service.IProductSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeCTL {
    @Autowired
    private IProductSV iProductSV;
    @GetMapping("/home")
    public String getHome(Model model, @RequestParam("page") Optional<Integer> pageIndex, @RequestParam("size")Optional<Integer> pageSize){
        int page_index = pageIndex.orElse(1);
        int page_size = pageSize.orElse(9);
        Pageable pageable = PageRequest.of(page_index-1,page_size);
        Page<ProductDTO> productDTOS = iProductSV.getAll(pageable);
        int totalPage = productDTOS.getTotalPages();
        model.addAttribute("home_product",productDTOS);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("currentPage",page_index);
        return "user/homePage";
    }
}
