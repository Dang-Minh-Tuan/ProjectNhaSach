package com.example.nha_sach.dto;

import com.example.nha_sach.entities.Delivery;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeliveryDTO {
    private long id;
    private String name;

    public Delivery toEntity(DeliveryDTO deliveryDTO){
        return Delivery.builder().id(deliveryDTO.getId())
                                 .name(deliveryDTO.getName()).build();
    }
}
