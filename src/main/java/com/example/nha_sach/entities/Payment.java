package com.example.nha_sach.entities;

import com.example.nha_sach.dto.PaymentDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order")
    private DonHang donHangPay;

    public Payment toEntity(PaymentDTO paymentDTO){
        return Payment.builder().id(paymentDTO.getId())
                                .name(paymentDTO.getName())
                                .build();
    }
}
