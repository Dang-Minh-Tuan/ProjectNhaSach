package com.example.nha_sach.entities;

import com.example.nha_sach.dto.PublisherDTO;
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
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Publisher toEntity(PublisherDTO publisherDTO){
          return Publisher.builder().id(publisherDTO.getId())
                                    .name(publisherDTO.getName())
                                    .code(publisherDTO.getCode())
//                                    .products(publisherDTO.getProductDTOS().stream().map(x -> x.toEntity(x)).collect(Collectors.toSet()))
                                    .build();
    }

}
