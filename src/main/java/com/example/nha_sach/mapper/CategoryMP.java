package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.CategoryDTO;
import com.example.nha_sach.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMP {
    public CategoryDTO toDTO(Category category){
        return CategoryDTO.builder().id(category.getId())
                                    .name(category.getName())
                                    .code(category.getCode())
//                                    .products_cate(category.getProducts_cate().stream().map(x -> new ProductMP().toDTO(x)).toList())
                                    .build();
    }
}
