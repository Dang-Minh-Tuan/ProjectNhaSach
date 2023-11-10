package com.example.nha_sach.controller.userCTL;

import com.example.nha_sach.dto.AuthorDTO;
import com.example.nha_sach.dto.CategoryDTO;
import com.example.nha_sach.dto.ProductDTO;
import com.example.nha_sach.dto.PublisherDTO;
import com.example.nha_sach.service.IAuthorSV;
import com.example.nha_sach.service.ICategorySV;
import com.example.nha_sach.service.IProductSV;
import com.example.nha_sach.service.IPublisherSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ShopCTL {
    @Autowired
    private IProductSV iProductSV;
    @Autowired
    private ICategorySV iCategorySV;
    @Autowired
    private IAuthorSV iAuthorSV;
    @Autowired
    private IPublisherSV iPublisherSV;
    @GetMapping("/shop")
    public String getHome(Model model, @RequestParam("page") Optional<Integer> pageIndex, @RequestParam("size")Optional<Integer> pageSize){
        int page_index = pageIndex.orElse(1);
        int page_size = pageSize.orElse(9);
        Pageable pageable = PageRequest.of(page_index-1,page_size);
        Page<ProductDTO> productDTOS = iProductSV.getAll(pageable);
        int totalPage = productDTOS.getTotalPages();
        model.addAttribute("shop_product",productDTOS);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("currentPage",page_index);
        List<CategoryDTO> categoryDTOS = iCategorySV.getAllListCate();
        List<AuthorDTO> authorDTOS = iAuthorSV.getAllListAuthor();
        List<PublisherDTO> publisherDTOS = iPublisherSV.getAllListPub();
        model.addAttribute("listCate", categoryDTOS);
        model.addAttribute("listAuthor", authorDTOS);
        model.addAttribute("listPub", publisherDTOS);
        return "user/shop";
    }

    @GetMapping("shop/category/{categoryId}")
    public String showProductByCategoryId(@PathVariable Long categoryId, Model model){
        List<ProductDTO> productDTOS = iProductSV.findProductByIdCategory(categoryId);
        model.addAttribute("productsCate",productDTOS);
        return "user/productWithCategory :: productListCate";
    }

    @GetMapping("shop/author/{authorId}")
    public String showProductByAuthorId(@PathVariable Long authorId, Model model){
        List<ProductDTO> productDTOS = iProductSV.findProductByIdAuthor(authorId);
        model.addAttribute("productsAuth",productDTOS);
        return "user/productWithAuthor :: productListAuth";
    }

    @GetMapping("shop/publisher/{publisherId}")
    public String showProductByPublisherId(@PathVariable Long publisherId, Model model){
        List<ProductDTO> productDTOS = iProductSV.findProductByIdPublisher(publisherId);
        model.addAttribute("productsPub",productDTOS);
        return "user/productWithPublisher :: productListPub";
    }
}
