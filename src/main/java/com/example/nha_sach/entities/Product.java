package com.example.nha_sach.entities;

import com.example.nha_sach.dto.ProductDTO;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;
    private int amount;
    private String image;
    private String code;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_publisher")
    private Publisher publisher;
    @OneToMany(mappedBy = "productO",cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<DetailDH> detailDHS = new ArrayList<>();
    @ManyToMany( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "Category_Product",
            joinColumns = {@JoinColumn(name = "id_product")},
            inverseJoinColumns = {@JoinColumn(name = "id_category")}
    )
    private List<Category> productCategories = new ArrayList<>();

    @ManyToMany( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "Author_Product",
            joinColumns = {@JoinColumn(name = "id_product")},
            inverseJoinColumns = {@JoinColumn(name = "id_author")}
    )
    private List<Author> productAuthors = new ArrayList<>();

    public Product toEntity(ProductDTO productDTO){
        return Product.builder().id(productDTO.getId())
                                .name(productDTO.getName())
                                .price(productDTO.getPrice())
                                .amount(productDTO.getAmount())
                                .image(productDTO.getImage())
                                .code(productDTO.getCode())
//                                .donHangPro(productDTO.getDonHangDTOS().stream().map(x -> x.toEntity(x)).collect(Collectors.toSet()))
                                .productCategories(productDTO.getCategoryDTOS().stream().map(x -> x.toEntity(x)).toList())
                                .productAuthors(productDTO.getAuthorDTOS().stream().map(x -> x.toEntity(x)).toList())
                                .publisher(new Publisher().toEntity(productDTO.getPublisherDTO()))
                                .build();
    }
}
