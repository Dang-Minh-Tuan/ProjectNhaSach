package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.ProductDTO;
import com.example.nha_sach.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMP {
    public ProductDTO toDTO(Product product){
         return ProductDTO.builder().id(product.getId())
                                    .name(product.getName())
                                    .price(product.getPrice())
                                    .amount(product.getAmount())
                                    .image(product.getImage())
                                    .code(product.getCode())
                                    .detailDHsDTOS(product.getDetailDHS().stream().map(x-> new DetailDHsMP().toDTO(x)).toList())
                                    .categoryDTOS(product.getProductCategories().stream().map(x -> new CategoryMP().toDTO(x)).toList())
                                    .authorDTOS(product.getProductAuthors().stream().map(x -> new AuthorMP().toDTO(x)).toList())
                                    .publisherDTO(new PublisherMP().toDTO(product.getPublisher()))
                                    .build();
    }
}
