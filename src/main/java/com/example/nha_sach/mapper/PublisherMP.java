package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.PublisherDTO;
import com.example.nha_sach.entities.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherMP {
    public PublisherDTO toDTO(Publisher publisher){
        return PublisherDTO.builder().id(publisher.getId())
                                     .name(publisher.getName())
                                     .code(publisher.getCode())
//                                     .productDTOS(publisher.getProducts().stream().map(x -> new ProductMP().toDTO(x)).toList())
                                     .build();
    }
}
