package com.example.nha_sach.controller.adminCTL;

import com.example.nha_sach.dto.AuthorDTO;
import com.example.nha_sach.dto.CategoryDTO;
import com.example.nha_sach.service.IAuthorSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthorCTL {
    @Autowired
    private IAuthorSV iAuthorSV;

    @GetMapping("/author")
    public String getAllAuthor(Model model, @RequestParam("page") Optional<Integer> pageIndex, @RequestParam("size")Optional<Integer> pageSize){
        int page_index = pageIndex.orElse(1);
        int page_size = pageSize.orElse(3);
        Pageable pageable = PageRequest.of(page_index-1,page_size);
        Page<AuthorDTO> authorDTOS = iAuthorSV.getAll(pageable);
        int totalPage = authorDTOS.getTotalPages();
        model.addAttribute("list_author",authorDTOS);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("currentPage",page_index);
        // cái này là lấy dữ liệu nhập vào từ modal
        model.addAttribute("author",new AuthorDTO());
        return "admin/author";
    }

    @GetMapping("/addAuthor")
    public String SaveAuthor(Model model,AuthorDTO authorDTO){
        String name = authorDTO.getName();
        String code = iAuthorSV.checkSizeAuthor(name);
        authorDTO.setCode(code);
        iAuthorSV.Save(authorDTO);
        return "redirect:/author";
    }

    @PostMapping("/updateAuthor")
    public String Update(Model model, @ModelAttribute AuthorDTO authorDTO){
        Long id = authorDTO.getId();
        String name = authorDTO.getName();
        AuthorDTO authorDTOS = iAuthorSV.findAuthorById(id).get(0);
        String code = iAuthorSV.updateCodeAuthor(name,authorDTOS.getCode());
        authorDTO.setCode(code);
        iAuthorSV.Update(authorDTO);
        return "redirect:/author";
    }
}
