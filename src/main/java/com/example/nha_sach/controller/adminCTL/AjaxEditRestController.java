package com.example.nha_sach.controller.adminCTL;

import com.example.nha_sach.dto.AuthorDTO;
import com.example.nha_sach.dto.CategoryDTO;
import com.example.nha_sach.dto.ProductDTO;
import com.example.nha_sach.dto.PublisherDTO;
import com.example.nha_sach.service.IAuthorSV;
import com.example.nha_sach.service.ICategorySV;
import com.example.nha_sach.service.IProductSV;
import com.example.nha_sach.service.IPublisherSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AjaxEditRestController {
    @Autowired
    private ICategorySV iCategorySV;
    @Autowired
    private IAuthorSV iAuthorSV;
    @Autowired
    private IPublisherSV iPublisherSV;
    @Autowired
    private IProductSV iProductSV;
    @GetMapping("/editCategory/{id}")
    public ResponseEntity<CategoryDTO> getCate(Model model, @PathVariable("id") long id){
        CategoryDTO categoryDTO =  iCategorySV.findCategoriesById(id).get(0);
        return new ResponseEntity<>(categoryDTO, HttpStatusCode.valueOf(200));
    }
    @GetMapping("/editAuthor/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(Model model, @PathVariable("id") long id){
        AuthorDTO authorDTO =  iAuthorSV.findAuthorById(id).get(0);
        return new ResponseEntity<>(authorDTO, HttpStatusCode.valueOf(200));
    }
    @GetMapping("/editPublisher/{id}")
    public ResponseEntity<PublisherDTO> getPub(Model model, @PathVariable("id") long id){
        PublisherDTO publisherDTO =  iPublisherSV.findPublisherById(id).get(0);
        return new ResponseEntity<>(publisherDTO, HttpStatusCode.valueOf(200));
    }
    @GetMapping("/editProduct/{id}")
    public ResponseEntity<ProductDTO> getProduct(Model model, @PathVariable("id") long id){
        ProductDTO productDTO =  iProductSV.findProductById(id).get(0);
        return new ResponseEntity<>(productDTO, HttpStatusCode.valueOf(200));
    }
}
