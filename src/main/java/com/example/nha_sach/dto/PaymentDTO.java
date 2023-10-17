package com.example.nha_sach.dto;

import com.example.nha_sach.entities.Payment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentDTO {
    private long id;
    private String name;

    public Payment toEntity(PaymentDTO paymentDTO){
        return Payment.builder().id(paymentDTO.getId())
                                .name(paymentDTO.getName())
                                .build();
    }
}
