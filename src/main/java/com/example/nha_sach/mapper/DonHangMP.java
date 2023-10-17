package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.DonHangDTO;
import com.example.nha_sach.entities.DonHang;
import org.springframework.stereotype.Component;

@Component
public class DonHangMP {
    public DonHangDTO toDTO(DonHang donHang){
         return DonHangDTO.builder().id(donHang.getId())
                                    .name(donHang.getName())
                                    .date_created(donHang.getDate_created())
                                    .code(donHang.getCode())
//                                    .productDTOS(donHang.getProducts().stream().map(x -> new ProductMP().toDTO(x)).collect(Collectors.toSet()))
                                    .deliveryDTOS(donHang.getDeliveries().stream().map(x -> new DeliveryMP().toDTO(x)).toList())
                                    .paymentDTOS(donHang.getPayments().stream().map(x -> new PaymentMP().toDTO(x)).toList())
                                    .build();
    }
}
