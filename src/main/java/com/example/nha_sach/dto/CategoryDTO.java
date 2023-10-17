package com.example.nha_sach.dto;

import com.example.nha_sach.entities.Category;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO {
    private long id;
    private String name;
    private String code;
//    private List<ProductDTO> products_cate;

    public Category toEntity(CategoryDTO categoryDTO){
        return Category.builder().id(categoryDTO.getId())
                                 .name(categoryDTO.getName())
                                 .code(categoryDTO.getCode()).build();
    }
}
