package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.CustomerDTO;
import com.example.nha_sach.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMP {
    public CustomerDTO toDTO(Customer customer){
        return CustomerDTO.builder().id(customer.getId())
                                    .name(customer.getName())
                                    .phone(customer.getPhone())
                                    .email(customer.getEmail())
                                    .address(customer.getAddress())
                                    .userName(customer.getUserName())
                                    .passWord(customer.getPassWord())
                                    .code(customer.getCode())
                                    .billDTOS(customer.getBills().stream().map(x -> new BillMP().toDTO(x)).toList())
                                    .avatar(customer.getAvatar()).build();

    }
}
