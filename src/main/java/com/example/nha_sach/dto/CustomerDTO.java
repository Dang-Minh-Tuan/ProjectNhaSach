package com.example.nha_sach.dto;

import com.example.nha_sach.entities.Customer;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDTO {
    private long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String userName;
    private String passWord;
    private String code;
    private List<BillDTO> billDTOS;
    private String avatar;
    private MultipartFile file;

    public Customer toEntity(CustomerDTO customerDTO){
        return Customer.builder().id(customerDTO.getId())
                                 .name(customerDTO.getName())
                                 .phone(customerDTO.getPhone())
                                 .email(customerDTO.getEmail())
                                 .address(customerDTO.getAddress())
                                 .passWord(customerDTO.getPassWord())
                                 .code(customerDTO.getCode())
                                 .avatar(customerDTO.getAvatar())
                                 .build();
    }
}
