package com.example.nha_sach.entities;

import com.example.nha_sach.dto.EmployeeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;
    private String userName;
    private String passWord;
    private String code;
    private String avatar;
    @OneToMany(mappedBy = "employee",cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<DonHang> donHangs;
    public Employee toEntity(EmployeeDTO employeeDTO){
        return Employee.builder().id(employeeDTO.getId())
                                 .name(employeeDTO.getName())
                                 .phone(employeeDTO.getPhone())
                                 .userName(employeeDTO.getUserName())
                                 .passWord(employeeDTO.getPassWord())
                                 .code(employeeDTO.getCode())
                                 .avatar(employeeDTO.getAvatar())
                //                 .donHangs(employeeDTO.getDonHangDTOS().stream().map(x -> x.toEntity(x)).collect(Collectors.toSet()))
                                 .build();
    }
}
