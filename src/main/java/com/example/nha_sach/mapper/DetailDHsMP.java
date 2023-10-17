package com.example.nha_sach.mapper;

import com.example.nha_sach.dto.DetailDHsDTO;
import com.example.nha_sach.entities.DetailDH;
import org.springframework.stereotype.Component;

@Component
public class DetailDHsMP {
    public DetailDHsDTO toDTO(DetailDH detailDH){
        return DetailDHsDTO.builder().id(detailDH.getId())
                                     .name(detailDH.getName())
                                     .code(detailDH.getCode())
                                     .donHangDTO(new DonHangMP().toDTO(detailDH.getDonHang()))
                                     .productDTO(new ProductMP().toDTO(detailDH.getProductO()))
                                     .build();
    }
}
