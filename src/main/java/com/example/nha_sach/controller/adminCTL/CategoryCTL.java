package com.example.nha_sach.controller.adminCTL;

import com.example.nha_sach.dto.CategoryDTO;
import com.example.nha_sach.service.ICategorySV;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryCTL {
    @Autowired
    private ICategorySV iCategorySV;

    @GetMapping("/category")
    public String getAllCate(Model model, @RequestParam("page") Optional<Integer> pageIndex, @RequestParam("size")Optional<Integer> pageSize){
        int page_index = pageIndex.orElse(1);
        int page_size = pageSize.orElse(3);
        Pageable pageable = PageRequest.of(page_index-1,page_size);
        Page<CategoryDTO> categoryDTOS = iCategorySV.getAll(pageable);
        int totalPage = categoryDTOS.getTotalPages();
        model.addAttribute("list_category",categoryDTOS);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("currentPage",page_index);
        // cái này là lấy dữ liệu nhập vào từ modal
        model.addAttribute("category",new CategoryDTO());
        return "admin/category";
    }

    // Để cái add này của Modal là @PostMapping cx đc mà @GetMapping cx đc , miễn là bên ngoài trang html phải để đúng method
    @PostMapping("/addCate")
    public String SaveCate(Model model, @ModelAttribute CategoryDTO categoryDTO){
        String name = categoryDTO.getName();
        String code = iCategorySV.checkSizeCate(name);
        categoryDTO.setCode(code);
        iCategorySV.Save(categoryDTO);
        return "redirect:/category";
    }


    @PostMapping("/updateCate")
    public String Update(Model model, @ModelAttribute CategoryDTO categoryDTO){
        Long id = categoryDTO.getId();
        String name = categoryDTO.getName();
        CategoryDTO categoryDTOS = iCategorySV.findCategoriesById(id).get(0);
        String code = iCategorySV.updateCodeCate(name,categoryDTOS.getCode());
        categoryDTO.setCode(code);
        iCategorySV.Update(categoryDTO);
        return "redirect:/category";
    }

}
