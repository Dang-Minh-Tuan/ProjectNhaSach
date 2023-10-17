package com.example.nha_sach.controller.adminCTL;

import com.example.nha_sach.dto.AuthorDTO;
import com.example.nha_sach.dto.CategoryDTO;
import com.example.nha_sach.dto.ProductDTO;
import com.example.nha_sach.dto.PublisherDTO;
import com.example.nha_sach.service.IAuthorSV;
import com.example.nha_sach.service.ICategorySV;
import com.example.nha_sach.service.IProductSV;
import com.example.nha_sach.service.IPublisherSV;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Controller
public class ProductCTL {
    @Autowired
    private IProductSV iProductSV;
    @Autowired
    private ICategorySV iCategorySV;
    @Autowired
    private IAuthorSV iAuthorSV;
    @Autowired
    private IPublisherSV iPublisherSV;
    @Value("${upload.path}")
    private String path_file;
    @GetMapping("/product")
    public String getAllProduct(Model model, @RequestParam("page") Optional<Integer> pageIndex, @RequestParam("size")Optional<Integer> pageSize){
        int page_index = pageIndex.orElse(1);
        int page_size = pageSize.orElse(3);
        Pageable pageable = PageRequest.of(page_index-1,page_size);
        Page<ProductDTO> productDTOS = iProductSV.getAll(pageable);
        int totalPage = productDTOS.getTotalPages();
        model.addAttribute("list_product",productDTOS);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("currentPage",page_index);
        // cái này là lấy dữ liệu nhập vào từ modal
        model.addAttribute("product",new ProductDTO());
        model.addAttribute("product_update",new ProductDTO());
        List<CategoryDTO> categoryDTOS = iCategorySV.getAllListCate();
        model.addAttribute("cate_product",categoryDTOS);
        model.addAttribute("author_product",new AuthorDTO());
        model.addAttribute("pub_product",new PublisherDTO());
        return "admin/product";
    }

    @PostMapping("/addProduct")
    public String SaveProduct(@ModelAttribute ProductDTO productDTO, HttpServletRequest request,@RequestParam("AddCatePro") String nameCater){
        try {
            // set image
            MultipartFile multipartFile = productDTO.getFile();
            String nameImage = multipartFile.getOriginalFilename();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path_file+File.separator+nameImage)));
            stream.write(multipartFile.getBytes());
            stream.close();
            productDTO.setImage(nameImage);
            // set code
            String name = productDTO.getName();
            String code = iProductSV.checkSizeProduct(name);
            productDTO.setCode(code);
            // set cate
            List<CategoryDTO> categoryDTOS = iCategorySV.findCategoryByName(nameCater);
            productDTO.setCategoryDTOS(categoryDTOS);
            // set author
            String nameAuthor = request.getParameter("add_Pro_Aut");
            List<AuthorDTO> authorDTOS = iAuthorSV.CheckAndSaveAuthorInProduct(nameAuthor);
            productDTO.setAuthorDTOS(authorDTOS);
            // set publisher
            String namePub = request.getParameter("add_Pro_Pub");
            PublisherDTO publisherDTO = iPublisherSV.CheckAndSavePublisherInProduct(namePub);
            productDTO.setPublisherDTO(publisherDTO);
            System.out.println("aa");
            // save product
            iProductSV.Save(productDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/product";
    }

    @PostMapping("/updateProduct")
    public String UpdateProduct(@ModelAttribute ProductDTO productDTO,HttpServletRequest request,@RequestParam("UpdateCatePro") String nameCater){
        try {
            // set image
            MultipartFile multipartFile = productDTO.getFile();
            String nameImage = multipartFile.getOriginalFilename();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path_file+File.separator+nameImage)));
            stream.write(multipartFile.getBytes());
            stream.close();
            productDTO.setImage(nameImage);
            // set code
            String name = productDTO.getName();
            String code = iProductSV.updateCodeProduct(name,productDTO.getCode());
            productDTO.setCode(code);
            // set cate
            List<CategoryDTO> categoryDTOS = iCategorySV.findCategoryByName(nameCater);
            productDTO.setCategoryDTOS(categoryDTOS);
            // set author
            String nameAuthor = request.getParameter("update_Pro_Aut");
            List<AuthorDTO> authorDTOS = iAuthorSV.CheckAndSaveAuthorInProduct(nameAuthor);
            productDTO.setAuthorDTOS(authorDTOS);
            // set publisher
            String namePub = request.getParameter("update_Pro_Pub");
            PublisherDTO publisherDTO = iPublisherSV.CheckAndSavePublisherInProduct(namePub);
            productDTO.setPublisherDTO(publisherDTO);
            // save product
            iProductSV.Update(productDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/product";
    }
}
