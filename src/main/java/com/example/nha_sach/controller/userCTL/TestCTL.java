package com.example.nha_sach.controller.userCTL;

import com.example.nha_sach.dto.CategoryDTO;
import com.example.nha_sach.dto.ProductDTO;
import com.example.nha_sach.service.ICategorySV;
import com.example.nha_sach.service.IProductSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TestCTL {
    @Autowired
    private ICategorySV iCategorySV;
    @Autowired
    private IProductSV iProductSV;

//    @GetMapping("/test")
//    public String getTest (Model model){
//        List<CategoryDTO> categoryDTOS = iCategorySV.getAllListCate();
//        List<ProductDTO> productDTOS = iProductSV.getAllListProduct();
//        model.addAttribute("testCate", categoryDTOS);
//        model.addAttribute("testPro", productDTOS);
//        return "user/test";
//    }
// Trả về trang web chứa danh sách sản phẩm và danh sách thể loại
@GetMapping("/test")
public String showProducts(Model model) {
    // Lấy danh sách các sản phẩm và các thể loại từ service
    List<ProductDTO> products = iProductSV.getAllListProduct();
    List<CategoryDTO> categories = iCategorySV.getAllListCate();

    // Thêm danh sách các sản phẩm và các thể loại vào model
    model.addAttribute("products", products);
    model.addAttribute("categories", categories);

    // Trả về view tên là "products"
    return "user/test";
}

    // Xử lý yêu cầu ajax để lọc sản phẩm theo thể loại và phân trang
    @GetMapping("/products/filter")
    public String filterProducts(@RequestParam("category") String category,
                                 @RequestParam("page") int page,
                                 @RequestParam("size") int size,
                                 Model model) {
        // Lấy danh sách các sản phẩm theo thể loại đã chọn từ service
//        List<ProductDTO> products = iProductSV.findProductByCategory(category, page, size);
//
//        // Thêm danh sách các sản phẩm vào model
//        model.addAttribute("products", products);

        // Trả về view tên là "product-list" (chỉ bao gồm danh sách sản phẩm, không bao gồm menu dropdown)
        return "product-list";
    }





}
