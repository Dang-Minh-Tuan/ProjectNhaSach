package com.example.nha_sach.dto;

import com.example.nha_sach.entities.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    private long id;
    private String name;
    private int price;
    private int amount;
    private String image;
    private String code;
    private List<CategoryDTO> categoryDTOS;
    private List<AuthorDTO> authorDTOS;
    private PublisherDTO publisherDTO;
    private List<DetailDHsDTO> detailDHsDTOS;
    private MultipartFile file;

    public Product toEntity(ProductDTO productDTO){
        return Product.builder().id(productDTO.getId())
                                .name(productDTO.getName())
                                .price(productDTO.getPrice())
                                .amount(productDTO.getAmount())
                                .image(productDTO.getImage())
                                .code(productDTO.getCode())
                                .build();
    }

}
