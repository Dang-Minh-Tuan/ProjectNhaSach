package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.PaymentDTO;
import com.example.nha_sach.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMP {
    public PaymentDTO toDTO(Payment payment){
         return PaymentDTO.builder().id(payment.getId())
                                    .name(payment.getName())
                                    .build();
    }
}
