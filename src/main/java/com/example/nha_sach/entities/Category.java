package com.example.nha_sach.entities;

import com.example.nha_sach.dto.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String code;

    @ManyToMany(mappedBy = "productCategories", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Category toEntity(CategoryDTO categoryDTO){
        return Category.builder().id(categoryDTO.getId())
                                 .name(categoryDTO.getName())
                                 .code(categoryDTO.getCode())
//                                 .products_cate(categoryDTO.getProducts_cate().stream().map(x -> x.toEntity(x)).collect(Collectors.toSet()))
                                 .build();
    }
}
