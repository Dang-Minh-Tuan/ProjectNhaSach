package com.example.nha_sach.entities;

import com.example.nha_sach.dto.AuthorDTO;
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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String code;

    @ManyToMany(mappedBy = "productAuthors", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();
    public Author toEntity(AuthorDTO authorDTO){
         return Author.builder().id(authorDTO.getId())
                                .name(authorDTO.getName())
                                .code(authorDTO.getCode())
//                                .products_auth(authorDTO.getProducts_auth().stream().map(x -> x.toEntity(x)).collect(Collectors.toSet()))
                                .build();
    }
}
