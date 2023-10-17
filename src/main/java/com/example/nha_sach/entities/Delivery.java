package com.example.nha_sach.entities;

import com.example.nha_sach.dto.DeliveryDTO;
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
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order")
    private DonHang donHangDeli;

    public Delivery toEntity(DeliveryDTO deliveryDTO){
        return Delivery.builder().id(deliveryDTO.getId())
                                 .name(deliveryDTO.getName())
                                 .build();
    }
}
