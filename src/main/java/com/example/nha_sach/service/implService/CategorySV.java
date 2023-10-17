package com.example.nha_sach.service.implService;

import com.example.nha_sach.dto.CategoryDTO;
import com.example.nha_sach.entities.Category;
import com.example.nha_sach.mapper.CategoryMP;
import com.example.nha_sach.repository.CategoryRP;
import com.example.nha_sach.service.ICategorySV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySV implements ICategorySV {
    @Autowired
    private CategoryRP categoryRP;
    @Autowired
    private CategoryMP categoryMP;

    @Override
    public Page<CategoryDTO> getAll(Pageable pageable) {
        Page<CategoryDTO> categoryDTOS = categoryRP.findAll(pageable).map(x -> categoryMP.toDTO(x));
        return categoryDTOS;
    }

    @Override
    public boolean Save(CategoryDTO categoryDTO) {
        try {
            categoryRP.save(new Category().toEntity(categoryDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(CategoryDTO categoryDTO) {
        try {
            categoryRP.save(new Category().toEntity(categoryDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(CategoryDTO categoryDTO) {
        categoryRP.delete(new Category().toEntity(categoryDTO));
        return true;
    }

    public List<CategoryDTO> getAllListCate(){
        return categoryRP.findAll().stream().map(x -> categoryMP.toDTO(x)).toList();
    }

    public String checkSizeCate(String name){
        // Tạo ra chuỗi code rỗng
        String code = "";
        // Tạo ra 1 mảng chứa các Từ được cắt ra từ name được chuyền vào
        String list[] =  name.split(" ");
        for (String s : list) { // For each để lấy ra từng từ đã được tách ra khỏi chuỗi name
            if (!s.equals("") && !s.equals(null)) { // Check điều kiện để lấy ra các chữ cái  đầu tiên của từng chữ vừa được tách ra = split
                code += String.valueOf(s.charAt(0)); // Cộng từng chữ cái đầu tiên đấy lại với nhau tạo thành chuỗi mới rồi gán vào biến code
            }
        }
        List<CategoryDTO> categoryDTOS = getAllListCate(); // Lấy ra tất cả phần từ có trong Category
        // Check xem list Cate có rỗng hay ko
        if (categoryDTOS.size() == 0){
            String id = String.valueOf(1); // Rỗng thì code + 1
            code += id;
        }else {
            String regex = "[^0-9]";
            String index = ""; // Tạo ra 1 chuỗi index trống để tí nữa sẽ chứa các ptu số được cắt ra
            String[] code_last = categoryDTOS.get(categoryDTOS.size() -1).getCode().split(regex); // Lấy ra phần số trong code cuối cùng của list Cate
            for (String s : code_last) {
                index += s; // Hứng vào chuỗi index phần số vừa đc lấy ra từ chuỗi
            }
            int i = Integer.parseInt(index) + 1; // ép kiểu cho index để cộng với 1 -> tạo ra số tăng dần lớn hơn 1 đvi so với code đứng trước
            code += i; // cộng chuỗi để hoàn thành code
        }
        return code;
    }

    public List<CategoryDTO> findCategoriesById(Long id){
        List<CategoryDTO> categoryDTOS = categoryRP.findCategoriesById(id).stream().map(x -> categoryMP.toDTO(x)).toList();
        return categoryDTOS;
    }

    public List<CategoryDTO> findCategoryByName(String name){
        return categoryRP.findCategoryByName(name).stream().map(x -> categoryMP.toDTO(x)).toList();
    }

//    public CategoryDTO CheckAndSaveCateInProduct(String name){
//        CategoryDTO categoryDTO = findCategoryByName(name).get(0);
//        if (categoryDTO == null){
//            String code = checkSizeCate(name);
//            categoryDTO.setName(name);
//            categoryDTO.setCode(code);
//            Save(categoryDTO);
//        }
//        return categoryDTO;
//    }

    public String updateCodeCate(String name,String code){
        String codeUpdate = "";
        String list[] =  name.split(" ");
        for (String s : list) { // For each để lấy ra từng từ đã được tách ra khỏi chuỗi name
            if (!s.equals("") && !s.equals(null)) { // Check điều kiện để lấy ra các chữ cái  đầu tiên của từng chữ vừa được tách ra = split
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

}


