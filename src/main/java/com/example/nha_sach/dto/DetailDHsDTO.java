package com.example.nha_sach.dto;

import com.example.nha_sach.entities.DetailDH;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailDHsDTO {
    private long id;
    private String name;
    private String code;
    private DonHangDTO donHangDTO;
    private ProductDTO productDTO;
    public DetailDH toEntity(DetailDHsDTO detailDHsDTO){
        return DetailDH.builder().id(detailDHsDTO.getId())
                                 .name(detailDHsDTO.getName())
                                 .code(detailDHsDTO.getCode())
                                 .build();
    }
}
