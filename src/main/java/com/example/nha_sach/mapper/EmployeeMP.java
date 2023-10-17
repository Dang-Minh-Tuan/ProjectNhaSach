package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.EmployeeDTO;
import com.example.nha_sach.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMP {
    public EmployeeDTO toDTO(Employee employee){
        return EmployeeDTO.builder().id(employee.getId())
                                    .name(employee.getName())
                                    .phone(employee.getPhone())
                                    .userName(employee.getUserName())
                                    .passWord(employee.getPassWord())
                                    .code(employee.getCode())
                                    .donHangDTOS(employee.getDonHangs().stream().map(x -> new DonHangMP().toDTO(x)).toList())
                                    .avatar(employee.getAvatar()).build();
    }
}
