package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.AuthorDTO;
import com.example.nha_sach.entities.Author;
import org.springframework.stereotype.Component;


@Component
public class AuthorMP {
    public AuthorDTO toDTO(Author author){
        return AuthorDTO.builder().id(author.getId())
                                  .name(author.getName())
                                  .code(author.getCode())
//                                  .products_auth(author.getProducts_auth().stream().map(x -> new ProductMP().toDTO(x)).toList())
                                  .build();
    }
}
