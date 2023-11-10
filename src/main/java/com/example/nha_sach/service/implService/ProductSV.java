package com.example.nha_sach.service.implService;

import com.example.nha_sach.dto.CategoryDTO;
import org.apache.commons.lang3.StringUtils;
import com.example.nha_sach.dto.ProductDTO;
import com.example.nha_sach.entities.Product;
import com.example.nha_sach.mapper.ProductMP;
import com.example.nha_sach.repository.ProductRP;
import com.example.nha_sach.service.IProductSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSV implements IProductSV {
    @Autowired
    private ProductRP productRP;
    @Autowired
    private ProductMP productMP;

    @Override
    public Page<ProductDTO> getAll(Pageable pageable) {



        return productRP.findAll(pageable).map(x -> productMP.toDTO(x));
    }

    @Override
    public boolean Save(ProductDTO productDTO) {
        try {
            productRP.save(new Product().toEntity(productDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(ProductDTO productDTO) {
        try {
            productRP.save(new Product().toEntity(productDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(ProductDTO productDTO) {
        productRP.delete(new Product().toEntity(productDTO));
        return true;
    }

    public List<ProductDTO> getAllListProduct(){
        return productRP.findAll().stream().map(x -> productMP.toDTO(x)).toList();
    }

    public String checkSizeProduct(String name){
        // Tạo ra chuỗi code rỗng
        String code = "";
        // Tạo ra 1 mảng chứa các Từ được cắt ra từ name được chuyền vào
        String[] list;
        list = name.split(" ");
        for (String s : list) { // For each để lấy ra từng từ đã được tách ra khỏi chuỗi name
            if (list.length == 1){ // nếu name điền vào chỉ có 1 từ thì lấy luôn cả từ đấy
                code += s;
            }else if (!s.equals("") && !s.equals(null) && !StringUtils.isNumeric(s) && !s.equals("-")) { // Check điều kiện để lấy ra các chữ cái  đầu tiên của từng chữ vừa được tách ra = split
                code += String.valueOf(s.charAt(0)); // Cộng từng chữ cái đầu tiên đấy lại với nhau tạo thành chuỗi mới rồi gán vào biến code
            }
        }
        List<ProductDTO> productDTOS = getAllListProduct(); // Lấy ra tất cả phần từ có trong Category
        // Check xem list Cate có rỗng hay ko
        if (productDTOS.size() == 0){
            String id = String.valueOf(1); // Rỗng thì code + 1
            code += id;
        }else {
            String regex = "[^0-9]";
            String index = ""; // Tạo ra 1 chuỗi index trống để tí nữa sẽ chứa các ptu số được cắt ra
            String[] code_last = productDTOS.get(productDTOS.size() -1).getCode().split(regex); // Lấy ra phần số trong code cuối cùng của list Cate
            for (String s : code_last) {
                index += s; // Hứng vào chuỗi index phần số vừa đc lấy ra từ chuỗi
            }
            int i = Integer.parseInt(index) + 1; // ép kiểu cho index để cộng với 1 -> tạo ra số tăng dần lớn hơn 1 đvi so với code đứng trước
            code += i; // cộng chuỗi để hoàn thành code
        }
        return code;
    }

    public List<ProductDTO> findProductById(Long id){
        return productRP.findProductById(id).stream().map(x -> productMP.toDTO(x)).toList();
    }

    @Override
    public String updateCodeProduct(String name, String code) {
        String codeUpdate = "";
        String list[] =  name.split(" ");
        for (String s : list) { // For each để lấy ra từng từ đã được tách ra khỏi chuỗi name
            if (list.length == 1){ // nếu name điền vào chỉ có 1 từ thì lấy luôn cả từ đấy
                codeUpdate += s;
            }else if (!s.equals("") && !s.equals(null) && !StringUtils.isNumeric(s) && !s.equals("-")) { // Check điều kiện để lấy ra các chữ cái  đầu tiên của từng chữ vừa được tách ra = split
                codeUpdate += String.valueOf(s.charAt(0)); // Cộng từng chữ cái đầu tiên đấy lại với nhau tạo thành chuỗi mới rồi gán vào biến code
            }
        }
        String regex = "[^0-9]";
        String index = ""; // Tạo ra 1 chuỗi index trống để tí nữa sẽ chứa các ptu số được cắt ra
        String[] old_code = code.split(regex); // Lấy ra phần số trong code cuối cùng của list Cate
        for (String s : old_code) {
            index += s; // Hứng vào chuỗi index phần số vừa đc lấy ra từ chuỗi
        }
        codeUpdate += index; // cộng chuỗi để hoàn thành code
        return codeUpdate;
    }

    @Override
    public List<ProductDTO> findProductByIdCategory(Long idCate) {
        List<ProductDTO> list = productRP.findAll().stream().map(x -> productMP.toDTO(x)).toList();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductDTO productDTO: list
             ) {
            Long id = productDTO.getCategoryDTOS().get(0).getId();
            if (id.equals(idCate)){
                productDTOList.add(productDTO);
            }
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findProductByIdAuthor(Long idAuth) {
        List<ProductDTO> list = productRP.findAll().stream().map(x -> productMP.toDTO(x)).toList();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductDTO productDTO: list
        ) {
            Long id = productDTO.getAuthorDTOS().get(0).getId();
            if (id.equals(idAuth)){
                productDTOList.add(productDTO);
            }
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findProductByIdPublisher(Long idPub) {
        List<ProductDTO> list = productRP.findAll().stream().map(x -> productMP.toDTO(x)).toList();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductDTO productDTO: list
        ) {
            Long id = productDTO.getPublisherDTO().getId();
            if (id.equals(idPub)){
                productDTOList.add(productDTO);
            }
        }
        return productDTOList;
    }

}
