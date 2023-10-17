package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.DeliveryDTO;
import com.example.nha_sach.entities.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMP {
    public DeliveryDTO toDTO(Delivery delivery){
        return DeliveryDTO.builder().id(delivery.getId())
                                    .name(delivery.getName())
                                    .build();
    }
}
