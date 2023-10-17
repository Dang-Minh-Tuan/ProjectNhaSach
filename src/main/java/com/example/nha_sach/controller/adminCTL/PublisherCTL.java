package com.example.nha_sach.controller.adminCTL;

import com.example.nha_sach.dto.AuthorDTO;
import com.example.nha_sach.dto.PublisherDTO;
import com.example.nha_sach.service.IPublisherSV;
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
public class PublisherCTL {
    @Autowired
    private IPublisherSV iPublisherSV;
    @GetMapping("/publisher")
    public String getAllPub(Model model, @RequestParam("page") Optional<Integer> pageIndex, @RequestParam("size")Optional<Integer> pageSize){
        int page_index = pageIndex.orElse(1);
        int page_size = pageSize.orElse(3);
        Pageable pageable = PageRequest.of(page_index-1,page_size);
        Page<PublisherDTO> publisherDTOS = iPublisherSV.getAll(pageable);
        int totalPage = publisherDTOS.getTotalPages();
        model.addAttribute("list_publisher",publisherDTOS);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("currentPage",page_index);
        model.addAttribute("publisher",new PublisherDTO());
        return "admin/publisher";
    }

    @GetMapping("/addPub")
    public String SavePub(@ModelAttribute PublisherDTO publisherDTO){
        String name = publisherDTO.getName();
        String code = iPublisherSV.checkSizePub(name);
        publisherDTO.setCode(code);
        iPublisherSV.Save(publisherDTO);
        return "redirect:/publisher";
    }

    @PostMapping("/updatePub")
    public String Update(Model model, @ModelAttribute PublisherDTO publisherDTO){
        Long id = publisherDTO.getId();
        String name = publisherDTO.getName();
        PublisherDTO publisherDTOS = iPublisherSV.findPublisherById(id).get(0);
        String code = iPublisherSV.updateCodePub(name,publisherDTOS.getCode());
        publisherDTO.setCode(code);
        iPublisherSV.Update(publisherDTO);
        return "redirect:/publisher";
    }
}
