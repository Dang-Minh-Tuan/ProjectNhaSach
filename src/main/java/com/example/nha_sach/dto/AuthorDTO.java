package com.example.nha_sach.dto;

import com.example.nha_sach.entities.Author;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDTO {
    private long id;
    private String name;
    private String code;
//    private List<ProductDTO> products_auth;

    public Author toEntity(AuthorDTO authorDTO){
         return Author.builder().id(authorDTO.getId())
                                .name(authorDTO.getName())
                                .code(authorDTO.getCode()).build();
    }
}
