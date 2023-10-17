package com.example.nha_sach.dto;

import com.example.nha_sach.entities.DonHang;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DonHangDTO {
    private long id;
    private String name;
    private Date date_created;
    private String code;
    private List<DetailDHsDTO> detailDHsDTOS;
    private List<DeliveryDTO> deliveryDTOS;
    private List<PaymentDTO> paymentDTOS;

    public DonHang toEntity(DonHangDTO donHangDTO){
        return DonHang.builder().id(donHangDTO.getId())
                                .name(donHangDTO.getName())
                                .date_created(donHangDTO.getDate_created())
                                .code(donHangDTO.getCode()).build();
    }
}
