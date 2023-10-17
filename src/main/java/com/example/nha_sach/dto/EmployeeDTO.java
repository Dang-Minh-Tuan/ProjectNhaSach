package com.example.nha_sach.dto;

import com.example.nha_sach.entities.Employee;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDTO {
    private long id;
    private String name;
    private String phone;
    private String userName;
    private String passWord;
    private String code;
    private List<DonHangDTO> donHangDTOS;
    private String avatar;
    private MultipartFile file;

    public Employee toEntity(EmployeeDTO employeeDTO){
        return Employee.builder().id(employeeDTO.getId())
                                 .name(employeeDTO.getName())
                                 .phone(employeeDTO.getPhone())
                                 .userName(employeeDTO.getUserName())
                                 .passWord(employeeDTO.getPassWord())
                                 .code(employeeDTO.getCode())
                                 .avatar(employeeDTO.getAvatar()).build();
    }
}
