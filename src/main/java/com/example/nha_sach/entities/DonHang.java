package com.example.nha_sach.entities;

import com.example.nha_sach.dto.DonHangDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date date_created;
    private String code;
    @OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bill",referencedColumnName = "id")
    private Bill bill;

    @OneToMany(mappedBy = "donHang",cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<DetailDH> detailDHS = new ArrayList<>();
    @OneToMany(mappedBy = "donHangDeli",cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Delivery> deliveries = new ArrayList<>();
    @OneToMany(mappedBy = "donHangPay",cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Payment> payments = new ArrayList<>();
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee")
    private Employee employee;
    public DonHang toEntity(DonHangDTO donHangDTO){
        return DonHang.builder().id(donHangDTO.getId())
                                .name(donHangDTO.getName())
                                .date_created(donHangDTO.getDate_created())
                                .code(donHangDTO.getCode())
//                                .products(donHangDTO.getProductDTOS().stream().map(x -> x.toEntity(x)).collect(Collectors.toSet()))
                                .deliveries(donHangDTO.getDeliveryDTOS().stream().map(x -> x.toEntity(x)).toList())
                                .payments(donHangDTO.getPaymentDTOS().stream().map(x -> x.toEntity(x)).toList())
                                .build();
    }
}
