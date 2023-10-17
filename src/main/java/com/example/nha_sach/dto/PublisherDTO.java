package com.example.nha_sach.dto;

import com.example.nha_sach.entities.Publisher;
import lombok.*;

import java.util.List;;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PublisherDTO {
    private long id;
    private String name;
    private String code;
    private List<ProductDTO> productDTOS ;

    public Publisher toEntity(PublisherDTO publisherDTO){
          return Publisher.builder().id(publisherDTO.getId())
                                    .name(publisherDTO.getName())
                                    .code(publisherDTO.getCode()).build();
    }
}
