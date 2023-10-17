package com.example.nha_sach.entities;

import com.example.nha_sach.dto.CustomerDTO;
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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String userName;
    private String passWord;
    private String code;
    private String avatar;
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Bill> bills = new ArrayList<>();

    public Customer toEntity(CustomerDTO customerDTO){
        return Customer.builder().id(customerDTO.getId())
                                 .name(customerDTO.getName())
                                 .phone(customerDTO.getPhone())
                                 .email(customerDTO.getEmail())
                                 .address(customerDTO.getAddress())
                                 .userName(customerDTO.getUserName())
                                 .passWord(customerDTO.getPassWord())
                                 .code(customerDTO.getCode())
                                 .avatar(customerDTO.getAvatar())
                //                .bills(customerDTO.getBillDTOS().stream().map(x -> x.toEntity(x)).collect(Collectors.toSet()))
                                 .build();
    }
}
